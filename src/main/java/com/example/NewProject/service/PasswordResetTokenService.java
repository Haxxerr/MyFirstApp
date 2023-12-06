package com.example.NewProject.service;

import com.example.NewProject.service.dto.PasswordResetTokenDTO;
import com.example.NewProject.service.dto.UserDTO;

public interface PasswordResetTokenService {

  String generate();

  PasswordResetTokenDTO save(String token, UserDTO userDTO);

  PasswordResetTokenDTO update(PasswordResetTokenDTO passwordResetTokenDTO);

  PasswordResetTokenDTO get(String token);

  void delete(Long id);
}


