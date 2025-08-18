package dev.ayush.authenticationservice.controllers;

import dev.ayush.authenticationservice.Dtos.CreateRoleRequestDto;
import dev.ayush.authenticationservice.models.Role;
import dev.ayush.authenticationservice.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {this.roleService = roleService;}

    @PostMapping
    public ResponseEntity<Role> createRole(CreateRoleRequestDto requestDto) {
        Role role = roleService.createRole(requestDto.getName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
