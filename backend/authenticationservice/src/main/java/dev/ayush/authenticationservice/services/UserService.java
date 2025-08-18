package dev.ayush.authenticationservice.services;

import dev.ayush.authenticationservice.Dtos.UserDto;
import dev.ayush.authenticationservice.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserDto getUserDetails(Long id) {
        return null;
    }

    public UserDto setUserRole(Long userId, List<Role> roleIds) {
        return null;
    }
}
