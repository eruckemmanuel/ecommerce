package com.example.ecommerce.services;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.UserModel;
import com.example.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    ModelMapper mapper = new ModelMapper();

    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public UserDto addUser(UserModel userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        UserModel user = repository.save(userInfo);
        return mapper.map(user, UserDto.class);
    }


}
