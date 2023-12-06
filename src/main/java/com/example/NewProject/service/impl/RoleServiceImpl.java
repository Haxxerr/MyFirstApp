package com.example.NewProject.service.impl;

import com.example.NewProject.domain.Role;
import com.example.NewProject.domain.enumeration.ERole;
import com.example.NewProject.exception.RoleException;
import com.example.NewProject.repository.RoleRepository;
import com.example.NewProject.service.RoleService;
import com.example.NewProject.service.dto.RoleDTO;
import com.example.NewProject.service.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

  private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

  private final RoleRepository roleRepository;

  private final RoleMapper roleMapper;

  public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
    this.roleRepository = roleRepository;
    this.roleMapper = roleMapper;
  }

  @Override
  public Set<RoleDTO> findAll() {
    log.info("Fetching all roles.");
    List<Role> roleList = roleRepository.findAll();
    return roleMapper.toDto(Set.copyOf(roleList));
  }

  @Override
  public RoleDTO findByName(ERole name) {
    log.info("Fetching role {}", name);
    Optional<Role> role = roleRepository.findByName(name);
    return role.map(roleMapper::toDto)
      .orElseThrow(() -> new RoleException("ErrorRole not found: " + ERole.ROLE_ADMIN.getRoleName()));
  }

  @Override
  public RoleDTO saveRole(RoleDTO roleDTO) {
    log.info("Adding new role {} to database", roleDTO.getName());
    return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDTO)));
  }
}
