package dev.ayush.productservice.security;

import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class JwtObject {
    private String email;
    private Long id;
    private Date createdAt;
    private Date expiresAt;
    private List<Role> roles = new ArrayList<>();
}
