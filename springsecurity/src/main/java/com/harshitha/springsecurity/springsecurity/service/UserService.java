package com.harshitha.springsecurity.springsecurity.service;

import com.harshitha.springsecurity.springsecurity.dto.LoginDTO;
import com.harshitha.springsecurity.springsecurity.dto.SignUpDTO;
import com.harshitha.springsecurity.springsecurity.dto.UserDTO;
import com.harshitha.springsecurity.springsecurity.entities.User;
import com.harshitha.springsecurity.springsecurity.exceptions.ResourceNotFoundException;
import com.harshitha.springsecurity.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with the email "+ username+" not found"));
    }


    public UserDTO signup(SignUpDTO signUpDTO){
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with the email "+ signUpDTO.getEmail()+" already exists");
        }

        User toBeCreatedUser = modelMapper.map(signUpDTO, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        User saveduser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(saveduser, UserDTO.class);
    }

}
