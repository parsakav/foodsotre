package org.example.sotre.service;


import org.example.sotre.dto.UserDto;
import org.example.sotre.model.Log;
import org.example.sotre.repository.LogRepository;
import org.example.sotre.repository.UserRepository;
import org.example.sotre.service.exceptions.UnverifiedUserException;
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
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private LogRepository logRepository;
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
        if(!u.isVerified() && !u.getRole().equalsIgnoreCase("ROLE_ADMIN")){
            Log log = new Log();
            log.setAction("Un verified login attempt");
            log.setEmail(u.getEmail());
            logRepository.save(log);
            throw new UnverifiedUserException("User isn't verified");
        }

        Log log = new Log();
        log.setAction("Login Successful");
        log.setEmail(u.getEmail());
        logRepository.save(log);

        return new User(u.getEmail(),
                u.getPassword(),
                List.of(new SimpleGrantedAuthority(u.getRole())));
    }


}
