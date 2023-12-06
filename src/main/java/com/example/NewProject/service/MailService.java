package com.example.NewProject.service;

import com.example.NewProject.payload.response.MessageResponse;
import com.example.NewProject.service.dto.ConfirmationTokenDTO;
import com.example.NewProject.service.dto.PasswordResetTokenDTO;

import java.util.Locale;

public interface MailService {

  MessageResponse sendAfterRegistration(ConfirmationTokenDTO confirmationTokenDTO, Locale locale);

  MessageResponse sendAfterEmailConfirmation(ConfirmationTokenDTO confirmationTokenDTO, Locale locale);

  MessageResponse sendPasswordResetMail(PasswordResetTokenDTO passwordResetTokenDTO, Locale locale);

  MessageResponse sendAfterPasswordChange(PasswordResetTokenDTO passwordResetTokenDTO, Locale locale);
}
