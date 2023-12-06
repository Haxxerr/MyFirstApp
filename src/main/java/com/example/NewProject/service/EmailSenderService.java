package com.example.NewProject.service;

import com.example.NewProject.service.dto.MailDTO;

public interface EmailSenderService {

  void sendEmail(MailDTO mailDTO);
}
