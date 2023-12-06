package com.example.NewProject.service.impl;

import com.example.NewProject.payload.request.NewPasswordPutRequest;
import com.example.NewProject.payload.response.MessageResponse;
import com.example.NewProject.service.PasswordResetService;
import com.example.NewProject.service.PasswordResetTokenService;
import com.example.NewProject.service.UserService;
import com.example.NewProject.service.dto.PasswordResetTokenDTO;
import com.example.NewProject.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

  private final Logger log = LoggerFactory.getLogger(PasswordResetServiceImpl.class);

  private final PasswordResetTokenService passwordResetTokenService;
  private final UserService userService;
  private final PasswordEncoder encoder;

  public PasswordResetServiceImpl(PasswordResetTokenService passwordResetTokenService, UserService userService,
                                  PasswordEncoder encoder) {
    this.passwordResetTokenService = passwordResetTokenService;
    this.userService = userService;
    this.encoder = encoder;
  }

  @Override
  public MessageResponse saveNewPassword(PasswordResetTokenDTO passwordResetTokenDTO, NewPasswordPutRequest request) {
    log.debug("Request to save new password.");
    UserDTO userDTO = userService.getUser(passwordResetTokenDTO.getEmail());
    UserDTO updatedUserDTO =
      UserDTO.builder(userDTO).password(encoder.encode(request.getNewPasswordHash())).build();
    userService.save(updatedUserDTO);
    passwordResetTokenService.update(PasswordResetTokenDTO.builder(passwordResetTokenDTO)
      .confirmDate(LocalDateTime.now()).build());
    return new MessageResponse("Password changed successfully.");
  }
}
