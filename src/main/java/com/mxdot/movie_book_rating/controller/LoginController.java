package com.mxdot.movie_book_rating.controller;

import com.mxdot.movie_book_rating.dto.*;
import com.mxdot.movie_book_rating.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getLoginId(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletRequest request, HttpServletResponse response) {
        return authService.logout(request, response);
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest.getEmail(), registerRequest.getUsername(), registerRequest.getPassword());
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(@CookieValue String refreshToken) {
        return authService.attemptRefresh(refreshToken);
    }
}
