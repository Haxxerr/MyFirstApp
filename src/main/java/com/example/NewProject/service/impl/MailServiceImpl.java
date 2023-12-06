package com.example.NewProject.service.impl;



import com.example.NewProject.domain.Mail;
import com.example.NewProject.domain.enumeration.DictionaryType;
import com.example.NewProject.domain.enumeration.Language;
import com.example.NewProject.domain.enumeration.MailEvent;
import com.example.NewProject.payload.response.MessageResponse;
import com.example.NewProject.repository.MailRepository;
import com.example.NewProject.service.DictionaryService;
import com.example.NewProject.service.EmailSenderService;
import com.example.NewProject.service.MailService;
import com.example.NewProject.service.dto.ConfirmationTokenDTO;
import com.example.NewProject.service.dto.DictionaryDTO;
import com.example.NewProject.service.dto.MailDTO;
import com.example.NewProject.service.dto.PasswordResetTokenDTO;
import com.example.NewProject.service.mapper.MailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class MailServiceImpl implements MailService {

  @Value("15")
  private Long tokenValidityTimeInMinutes;

  @Value("http://localhost:8080")
  private String myAppUrl;
  private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

  private static final String API_PATH = "/api/auth";
  private static final String LOGIN_PAGE_URL = "loginPageUrl";


  private final MailRepository mailRepository;
  private final DictionaryService dictionaryService;
  private final TemplateEngine templateEngine;
  private final MailMapper mailMapper;
  private final EmailSenderService emailSenderService;

  public MailServiceImpl( MailRepository mailRepository, DictionaryService dictionaryService, TemplateEngine templateEngine, MailMapper mailMapper, EmailSenderService emailSenderService) {
    this.mailRepository = mailRepository;
    this.dictionaryService = dictionaryService;
    this.templateEngine = templateEngine;
    this.mailMapper = mailMapper;
    this.emailSenderService = emailSenderService;
  }

  @Override
  public MessageResponse sendAfterRegistration(ConfirmationTokenDTO confirmationTokenDTO, Locale locale) {
    log.debug("Request to send email to confirm user registration: {}", confirmationTokenDTO.getEmail());
    Context context = new Context(locale);
    context.setVariable(LOGIN_PAGE_URL, myAppUrl + "/login");
    context.setVariable("emailConfirmationLink",
      myAppUrl + API_PATH + "/confirm?token=" + confirmationTokenDTO.getConfirmationToken());
    context.setVariable("tokenValidityTime", tokenValidityTimeInMinutes);
    //template to send as string
    String content = templateEngine.process("mail-after-registration", context);
    String title = chooseTitle(MailEvent.AFTER_REGISTRATION, locale);
    MailDTO mailDTO = MailDTO.builder()
      .userId(confirmationTokenDTO.getUserId())
      .userEmail(confirmationTokenDTO.getEmail())
      .title(title)
      .content(content)
      .mailEvent(MailEvent.AFTER_REGISTRATION)
      .language(Language.from(locale.getLanguage())).build();
    emailSenderService.sendEmail(mailDTO);
    Mail mail = mailMapper.toEntity(mailDTO);
    mailRepository.save(mail);
    return new MessageResponse("Register successful");
  }

  @Override
  public MessageResponse sendAfterEmailConfirmation(ConfirmationTokenDTO confirmationTokenDTO, Locale locale) {
    log.debug("Request to send email to confirm user activation:");
    Context context = new Context(locale);
    context.setVariable(LOGIN_PAGE_URL, myAppUrl + "/login");
    //template to send as string
    String content = templateEngine.process("mail-after-confirmation", context);
    String title = chooseTitle(MailEvent.AFTER_CONFIRMATION, locale);
    MailDTO mailDTO = MailDTO.builder()
      .userId(confirmationTokenDTO.getUserId())
      .userEmail(confirmationTokenDTO.getEmail())
      .title(title)
      .content(content)
      .mailEvent(MailEvent.AFTER_CONFIRMATION)
      .language(Language.from(locale.getLanguage()))
      .createDatetime(LocalDateTime.now()).build();
    emailSenderService.sendEmail(mailDTO);
    Mail mail = mailMapper.toEntity(mailDTO);
    mailRepository.save(mail);
    return new MessageResponse("Register successful");
  }

  @Override
  public MessageResponse sendPasswordResetMail(PasswordResetTokenDTO passwordResetTokenDTO, Locale locale) {
    log.debug("Request to send email to reset password");
    Context context = new Context(locale);
    context.setVariable(LOGIN_PAGE_URL, myAppUrl + "/login");
    context.setVariable("passwordResetLink",
      myAppUrl + API_PATH + "/reset-password?token=" + passwordResetTokenDTO.getPasswordResetToken());
    context.setVariable("tokenValidityTime", tokenValidityTimeInMinutes);
    String content = templateEngine.process("mail-password-reset", context);
    String title = chooseTitle(MailEvent.PASSWORD_RESET, locale);
    MailDTO mailDTO = MailDTO.builder()
      .userId(passwordResetTokenDTO.getUserId())
      .userEmail(passwordResetTokenDTO.getEmail())
      .title(title)
      .content(content)
      .mailEvent(MailEvent.PASSWORD_RESET)
      .language(Language.from(locale.getLanguage())).build();
    emailSenderService.sendEmail(mailDTO);
    Mail mail = mailMapper.toEntity(mailDTO);
    mailRepository.save(mail);
    return new MessageResponse("Password reset token sent with token: " + passwordResetTokenDTO.getPasswordResetToken());
  }

  @Override
  public MessageResponse sendAfterPasswordChange(PasswordResetTokenDTO passwordResetTokenDTO, Locale locale) {
    log.debug("Request to send email to confirm password reset.");
    Context context = new Context(locale);
    context.setVariable(LOGIN_PAGE_URL, myAppUrl + "/login");
    //template to send as string
    String content = templateEngine.process("mail-after-password-change", context);
    String title = chooseTitle(MailEvent.AFTER_PASSWORD_CHANGE, locale);
    MailDTO mailDTO = MailDTO.builder()
      .userId(passwordResetTokenDTO.getUserId())
      .userEmail(passwordResetTokenDTO.getEmail())
      .title(title)
      .content(content)
      .mailEvent(MailEvent.AFTER_PASSWORD_CHANGE)
      .language(Language.from(locale.getLanguage()))
      .createDatetime(LocalDateTime.now()).build();
    emailSenderService.sendEmail(mailDTO);
    Mail mail = mailMapper.toEntity(mailDTO);
    mailRepository.save(mail);
    return new MessageResponse("Password changed successfully");
  }

  private String chooseTitle(MailEvent mailEvent, Locale locale) {

    List<DictionaryDTO> list = dictionaryService.getDictionary(DictionaryType.EMAIL_TITLES, locale);

    for (DictionaryDTO dictionaryData : list) {
      if (dictionaryData.getCode().equals(mailEvent.getCode())) {
        return dictionaryData.getValue();
      }
    }

    throw new IllegalArgumentException("No email title!");

  }
}
