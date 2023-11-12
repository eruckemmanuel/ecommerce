package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.AuthRequest;
import com.example.ecommerce.model.UserModel;
import com.example.ecommerce.services.JwtService;
import com.example.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    private UserDto registerUser(@RequestBody UserModel userData){
        return userService.addUser(userData);
    }

    @PostMapping("/authenticate")
    private Map<String, String> authenticate(@RequestBody AuthRequest loginData){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
        if(authentication.isAuthenticated()){
            HashMap<String, String> response = new HashMap<>();
            response.put("token", jwtService.generateToken(loginData.getUsername()));
            return response;
        }else {
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
