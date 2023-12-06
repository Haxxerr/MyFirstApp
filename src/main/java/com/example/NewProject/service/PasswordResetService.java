package com.example.NewProject.service;

import com.example.NewProject.payload.request.NewPasswordPutRequest;
import com.example.NewProject.payload.response.MessageResponse;
import com.example.NewProject.service.dto.PasswordResetTokenDTO;

public interface PasswordResetService {

  MessageResponse saveNewPassword(PasswordResetTokenDTO passwordResetTokenDTO, NewPasswordPutRequest request);
}
