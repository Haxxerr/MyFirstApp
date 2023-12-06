package com.example.NewProject.controller;

import com.example.NewProject.exception.EmailAlreadyExistsException;
import com.example.NewProject.exception.EmailNotFoundException;
import com.example.NewProject.exception.PasswordNotMatchException;
import com.example.NewProject.exception.UserNameAlreadyExistsException;
import com.example.NewProject.payload.request.*;
import com.example.NewProject.payload.response.JwtResponse;
import com.example.NewProject.payload.response.MessageResponse;
import com.example.NewProject.security.jwt.JwtUtils;
import com.example.NewProject.security.service.UserDetailsImpl;
import com.example.NewProject.service.*;
import com.example.NewProject.service.dto.ConfirmationTokenDTO;
import com.example.NewProject.service.dto.PasswordResetTokenDTO;
import com.example.NewProject.service.dto.UserDTO;
import com.example.NewProject.utils.TokenUtil;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/auth")
public class UserAuthController {

  @Value("${tokenValidityTimeInMinutes}")
  private Long tokenValidityTimeInMinutes;

  private final Logger log = LoggerFactory.getLogger(UserAuthController.class);

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final UserService userService;
  private final ConfirmationTokenService confirmationTokenService;
  private final PasswordResetTokenService passwordResetTokenService;
  private final PasswordResetService passwordResetService;
  private final MailService mailService;


  public UserAuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                            UserService userService,
                            ConfirmationTokenService confirmationTokenService,
                            PasswordResetTokenService passwordResetTokenService, PasswordResetService passwordResetService, MailService mailService) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.userService = userService;
    this.confirmationTokenService = confirmationTokenService;
    this.passwordResetTokenService = passwordResetTokenService;
    this.passwordResetService = passwordResetService;
    this.mailService = mailService;
  }


  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    log.debug("REST request to login user {}", loginRequest);
    LoginRequest updatedRequest =
      LoginRequest.builder(loginRequest).username(loginRequest.getUsername().toLowerCase()).build();
    UsernamePasswordAuthenticationToken token =
      new UsernamePasswordAuthenticationToken(updatedRequest.getUsername(), updatedRequest.getPassword());
    Authentication authentication = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
      .collect(Collectors.toList());
    return ResponseEntity.ok(new JwtResponse(jwt,
      userDetails.getId(),
      userDetails.getUsername(),
      userDetails.getEmail(),
      roles));
  }

  @PostMapping("/register")
  @Transactional
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
    log.debug("REST request to register new user {}", registerRequest);
    RegisterRequest updatedRequest =
      RegisterRequest.builder(registerRequest).username(registerRequest.getUsername().toLowerCase()).build();
    if (isUsernameTaken(updatedRequest.getUsername())) {
      throw new UserNameAlreadyExistsException("Error. Username is already in use.");
    }

    if (isEmailTaken(updatedRequest.getEmail())) {
      throw new EmailAlreadyExistsException("Error. Email is already in use!");
    }

    UserDTO savedUser = userService.register(updatedRequest);
    String generatedToken = confirmationTokenService.generateToken();
    ConfirmationTokenDTO confirmationTokenDTO = confirmationTokenService.saveToken(generatedToken, savedUser);

    MessageResponse response = mailService.sendAfterRegistration(confirmationTokenDTO, new Locale("pl"));
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/confirm")
  public String confirmRegistration(@RequestParam("token") String token) {
    log.debug("REST request to confirm user registration. Token: {}", token);
    ConfirmationTokenDTO confirmationTokenDTO = confirmationTokenService.getConfirmationToken(token);

    TokenUtil.validateTokenTime(confirmationTokenDTO.getCreateDate(), tokenValidityTimeInMinutes);
    mailService.sendAfterEmailConfirmation(confirmationTokenDTO, new Locale("pl"));
    return userService.confirm(token);
  }

  @PutMapping("/request-password-reset")
  @Transactional
  public ResponseEntity<?> passwordReset(@Valid @NotNull @RequestBody PasswordResetRequest passwordResetRequest) {
    log.debug("REST request to set new password for user: {}", passwordResetRequest.getEmail());

    if (!isEmailTaken(passwordResetRequest.getEmail())) {
      throw new EmailNotFoundException("Email not found");
    }
    UserDTO userDTO = userService.getUser(passwordResetRequest.getEmail());
    String token = passwordResetTokenService.generate();
    PasswordResetTokenDTO passwordResetTokenDTO = passwordResetTokenService.save(token, userDTO);
    MessageResponse response = mailService.sendPasswordResetMail(passwordResetTokenDTO, new Locale("pl"));
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/reset-password")
  public ResponseEntity<?> newPasswordPut(@RequestParam("token") String token,
                                          @RequestBody NewPasswordPutRequest request) {
    log.debug("REST request to set new password by token: {}", token);
    validatePasswordMatch(request);
    PasswordResetTokenDTO passwordResetTokenDTO = passwordResetTokenService.get(token);

    TokenUtil.validateTokenTime(passwordResetTokenDTO.getCreateDate(), tokenValidityTimeInMinutes);
    MessageResponse response = passwordResetService.saveNewPassword(passwordResetTokenDTO, request);
    mailService.sendAfterPasswordChange(passwordResetTokenDTO, new Locale("pl"));
    return ResponseEntity.ok().body(response);
  }

  private boolean isEmailTaken(String email) {
    return Boolean.TRUE.equals(userService.isEmailExists(email));
  }

  private boolean isUsernameTaken(String userName) {
    return Boolean.TRUE.equals(userService.isUserExists(userName));
  }

  private void validatePasswordMatch(NewPasswordPutRequest request) {
    if (!request.getNewPasswordHash().equals(request.getNewPasswordHashRepeat())) {
      throw new PasswordNotMatchException("Password not match");
    }
  }

}
