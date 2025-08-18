package dev.ayush.authenticationservice.Dtos;

import dev.ayush.authenticationservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRolesRequestDto {
    private List<Role> roleIds;
}
