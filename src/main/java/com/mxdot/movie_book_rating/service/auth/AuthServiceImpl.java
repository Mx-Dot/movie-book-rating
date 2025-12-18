package com.mxdot.movie_book_rating.service.auth;

import com.mxdot.movie_book_rating.data.model.User;
import com.mxdot.movie_book_rating.dto.LoginResponse;
import com.mxdot.movie_book_rating.dto.LogoutResponse;
import com.mxdot.movie_book_rating.dto.RegistrationResponse;
import com.mxdot.movie_book_rating.repository.user.UserRepository;
import com.mxdot.movie_book_rating.security.JWTUtility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtility jwtUtility;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(String loginId, String password) {
        final User user = userRepository.findByUsername(loginId)
                .or(() -> userRepository.findByEmail(loginId))
                .orElse(null);

        if (user == null) return new LoginResponse(false, "Login failed", null, null);

        if (passwordEncoder.matches(password, user.getPassword())) {
            final String auth = jwtUtility.generateToken(loginId);
            return new LoginResponse(true, "Login successful", user.getUsername(), auth);
        }
        return new LoginResponse(false, "Login failed", null, null);
    }

    @Override
    public RegistrationResponse register(String email, String username, String password) {
        final User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return new RegistrationResponse(true, email, username, "Registration successful");
    }

    @Override
    public LogoutResponse logout(HttpServletRequest request) {
        final String auth = request.getHeader("Authorization");
        SecurityContextHolder.clearContext();
        return new LogoutResponse(true, "Logout successful");
    }

    @Override
    public LoginResponse attemptRefresh(String refreshToken) {
        if (refreshToken == null)
            return new LoginResponse(false, "Refresh token not found", null, null);

        final String username = jwtUtility.getUsernameFromToken(refreshToken);

        if (!jwtUtility.validateJwtToken(refreshToken))
            return new LoginResponse(false, "Invalid refresh token", username, null);
        final String newToken = jwtUtility.generateToken(username);
        return new LoginResponse(true, "Refresh successful", username, newToken);
    }
}
