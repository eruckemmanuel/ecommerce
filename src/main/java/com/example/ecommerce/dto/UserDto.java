package com.example.ecommerce.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private boolean enabled;
    private List<GrantedAuthority> authorities;
}
