package com.example.NewProject.service;

import com.example.NewProject.service.dto.ConfirmationTokenDTO;
import com.example.NewProject.service.dto.UserDTO;


public interface ConfirmationTokenService {

  String generateToken();

  ConfirmationTokenDTO saveToken(String token, UserDTO userDTO);

  ConfirmationTokenDTO updateToken(ConfirmationTokenDTO confirmationTokenDTO);

  ConfirmationTokenDTO getConfirmationToken(String token);

  void deleteConfirmationToken(Long id);
}
