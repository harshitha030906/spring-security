package com.harshitha.springsecurity.springsecurity.controllers;

import com.harshitha.springsecurity.springsecurity.dto.LoginDTO;
import com.harshitha.springsecurity.springsecurity.dto.SignUpDTO;
import com.harshitha.springsecurity.springsecurity.dto.UserDTO;
import com.harshitha.springsecurity.springsecurity.entities.User;
import com.harshitha.springsecurity.springsecurity.service.AuthService;
import com.harshitha.springsecurity.springsecurity.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignUpDTO signUpDTO){
        UserDTO user =  userService.signup(signUpDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
        String token = authService.login(loginDTO);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
