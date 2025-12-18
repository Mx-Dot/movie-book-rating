package com.mxdot.movie_book_rating.controller;

import com.mxdot.movie_book_rating.dto.LoginRequest;
import com.mxdot.movie_book_rating.dto.LoginResponse;
import com.mxdot.movie_book_rating.dto.RegisterRequest;
import com.mxdot.movie_book_rating.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(HttpServletRequest request) {
        final String auth = request.getHeader("Authorization");
        final LoginResponse response = new LoginResponse(true, "Logout successful", null, null);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest.getUsername(), registerRequest.getPassword());
    }
}
