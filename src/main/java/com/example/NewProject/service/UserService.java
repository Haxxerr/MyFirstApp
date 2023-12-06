package com.example.NewProject.service;

import com.example.NewProject.payload.request.RegisterRequest;
import com.example.NewProject.service.dto.UserDTO;
import com.example.NewProject.service.filter.User.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

  UserDTO register(RegisterRequest request);

  String confirm(String token);

  UserDTO save(UserDTO userDTO);

  UserDTO update(UserDTO userDTO);

  List<UserDTO> findByFilter(UserFilter filter);

  Page<UserDTO> findByFilterAndPage(UserFilter filter, Pageable pageable);

  UserDTO findById(Long id);

  List<UserDTO> findAll();

  void delete(Long id);

  UserDTO getUser(String email);

  Boolean isUserExists(String userName);

  Boolean isEmailExists(String email);


}
