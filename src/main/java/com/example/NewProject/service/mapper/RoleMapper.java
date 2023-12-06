package com.example.NewProject.service.mapper;

import com.example.NewProject.domain.Role;
import com.example.NewProject.service.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

  default Role fromId(Long id) {
    if (id == null) {
      return null;
    }
    Role role = new Role();
    role.setId(id);
    return role;
  }
}
