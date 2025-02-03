package org.example.sotre.service;


import org.example.sotre.dto.UserDto;
import org.example.sotre.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto getUserByUsername(String username) {
      org.example.sotre.model.User u=  userRepository.findUserByEmail(username);
      UserDto userDto = new UserDto();
        BeanUtils.copyProperties(u,userDto);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        org.example.sotre.model.User u=  userRepository.findUserByEmail(username);


        if( u==null){
            throw new UsernameNotFoundException("Username or password is invalid");
        }

        return new User(u.getEmail(),
                u.getPassword(),
                List.of(new SimpleGrantedAuthority(u.getRole())));
    }


}
