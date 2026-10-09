package com.harshitha.springsecurity.springsecurity.filters;

import com.harshitha.springsecurity.springsecurity.entities.User;
import com.harshitha.springsecurity.springsecurity.service.JwtService;
import com.harshitha.springsecurity.springsecurity.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String requiredToken = token.split("Bearer ")[1];

        Long userId = jwtService.getUserIdFromToken(requiredToken);

        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = userService.getUserById(userId);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, null);

            authentication.setDetails(
                    new WebAuthenticationDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //to call the next filter in the filterchain
        filterChain.doFilter(request, response);

        //u can do something with the response
    }
}
