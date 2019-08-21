package com.dkasiian.controllers;

import com.dkasiian.model.entities.User;
import com.dkasiian.model.services.UserService;
import com.dkasiian.security.dto.AuthenticationRequestDto;
import com.dkasiian.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@Api(value = "Hotel Reservation System", tags = "auth")
@RestController
@RequestMapping(value = "auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Optional<User> user = userService.getUserByLogin(username);

            String token = user
                    .map(u -> jwtTokenProvider.createToken(username, u.getRole().name()))
                    .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));

            return ResponseEntity.ok(new HashMap<String, String>(){{
                put("username", username);
                put("token", token);
            }});
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }
}