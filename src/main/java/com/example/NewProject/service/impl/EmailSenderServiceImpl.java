package com.example.NewProject.service.impl;

import com.example.NewProject.exception.EmailSendFailException;
import com.example.NewProject.service.EmailSenderService;
import com.example.NewProject.service.dto.MailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmailSenderServiceImpl implements EmailSenderService {

  @Value("${platform.replyToEmail}")
  private String replyToEmail;

  @Value("${platform.fromEmail}")
  private String fromEmail;

  private static final Logger log = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

  private final JavaMailSender javaMailSender;


  public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void sendEmail(MailDTO sentEmailDTO) {
    log.debug("Sending email to: {}", sentEmailDTO.getUserEmail());
    MimeMessage mail = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setTo(sentEmailDTO.getUserEmail());
      helper.setReplyTo(replyToEmail);
      helper.setFrom(fromEmail);
      helper.setSubject(sentEmailDTO.getTitle());
      helper.setText(sentEmailDTO.getContent(), true);
    } catch (MessagingException e) {
      log.error("Failed to send email", e);
      throw new EmailSendFailException("Failed to send email");
    }

    sendMail(mail);

  }

  private void sendMail(MimeMessage mail) {
    new Thread(() -> {
      javaMailSender.send(mail);
    }).start();
  }

}
