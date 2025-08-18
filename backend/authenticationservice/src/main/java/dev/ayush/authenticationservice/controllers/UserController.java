package dev.ayush.authenticationservice.controllers;

import dev.ayush.authenticationservice.Dtos.CreateUserRolesRequestDto;
import dev.ayush.authenticationservice.Dtos.UserDto;
import dev.ayush.authenticationservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUserDetails(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRole(@PathVariable("id") Long userId, @RequestBody CreateUserRolesRequestDto requestDto) {
        UserDto userDto = userService.setUserRole(userId,requestDto.getRoleIds());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
