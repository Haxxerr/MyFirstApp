package com.example.NewProject.controller;
import com.example.NewProject.controller.util.PaginationUtil;
import com.example.NewProject.domain.User;
import com.example.NewProject.service.RoleService;
import com.example.NewProject.service.UserService;
import com.example.NewProject.service.dto.UserDTO;
import com.example.NewProject.service.filter.User.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
  private final Logger log = LoggerFactory.getLogger(UserController.class);

  private static final String ENTITY_NAME = "userController";
  private final UserService userService;


  private final RoleService roleService;

  public UserController(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @GetMapping(path = "/")
  public ResponseEntity<List<UserDTO>> getUsersByFilter(UserFilter userFilter) {
    log.debug("REST request to get all users by filter: {}", userFilter);
    List<UserDTO> result = userService.findByFilter(userFilter);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping(path = "/page")
  public ResponseEntity<List<UserDTO>> getUsersByFilterAndPage(UserFilter userFilter, Pageable pageable) {
    log.debug("REST request to get all users by filter and page: {},{}", userFilter, pageable);
    Page<UserDTO> result = userService.findByFilterAndPage(userFilter, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequestUri(), result);
    return ResponseEntity.ok().headers(headers).body(result.getContent());
  }

  @GetMapping(path = "/getById/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
    log.debug("REST request to get user by id: {}", id);
    UserDTO userDTO = userService.findById(id);
    return ResponseEntity.ok().body(userDTO);
  }

  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
    log.debug("REST request to delete user by id: {}", id);
    userService.delete(id);
    return ResponseEntity.ok().build();
  }
}
