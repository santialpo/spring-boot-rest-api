package com.unosquare.blog.springblogrestapi.controller;

import com.unosquare.blog.springblogrestapi.dto.JWTAuthResponse;
import com.unosquare.blog.springblogrestapi.dto.LoginDto;
import com.unosquare.blog.springblogrestapi.dto.SignUpDto;
import com.unosquare.blog.springblogrestapi.entity.Role;
import com.unosquare.blog.springblogrestapi.entity.User;
import com.unosquare.blog.springblogrestapi.repository.RoleRepository;
import com.unosquare.blog.springblogrestapi.repository.UserRepository;
import com.unosquare.blog.springblogrestapi.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@Api("Auto controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Rest API to login or Signup user to Blog app" )
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
       Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        return  ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if(userRepository.existsByUsername(signUpDto.getUsername())) {
            return  new ResponseEntity<>("username is already taken",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())) {
            return  new ResponseEntity<>("Email is already taken",
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles =  roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
