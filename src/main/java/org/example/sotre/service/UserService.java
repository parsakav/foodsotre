package org.example.sotre.service;

import org.example.sotre.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService  {
    UserDto getUserByUsername(String username);

    UserDetails loadUserByUsername(String username);
}
