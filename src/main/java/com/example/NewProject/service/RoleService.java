package com.example.NewProject.service;

import com.example.NewProject.domain.enumeration.ERole;
import com.example.NewProject.service.dto.RoleDTO;

import java.util.Set;

public interface RoleService {

  Set<RoleDTO> findAll();

  RoleDTO findByName(ERole name);

  RoleDTO saveRole(RoleDTO roleDTO);
}
