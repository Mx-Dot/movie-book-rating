package com.mxdot.movie_book_rating.service.auth;

import com.mxdot.movie_book_rating.data.model.User;
import com.mxdot.movie_book_rating.dto.LoginResponse;
import com.mxdot.movie_book_rating.repository.user.UserRepository;
import com.mxdot.movie_book_rating.security.JWTUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public LoginResponse login(String username, String password) {
        final User user = userRepository.findByUsername(username).isPresent() ? userRepository.findByUsername(username).get() : null;

        if(user == null) return new LoginResponse(false, "Login failed", null, null);

        if (passwordEncoder.matches(password, user.getPassword())) {
            final String auth = jwtUtility.generateToken(username);
            return new LoginResponse(true, "Login successful", user.getUsername(), auth);
        }
        return new LoginResponse(false, "Login failed", null, null);
    }

    @Override
    public LoginResponse register(String username, String password) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return new LoginResponse(true, "Registration successful", username, null);
    }
}
