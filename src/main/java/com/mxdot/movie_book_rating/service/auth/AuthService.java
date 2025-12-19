package com.mxdot.movie_book_rating.service.auth;

import com.mxdot.movie_book_rating.dto.LoginResponse;
import com.mxdot.movie_book_rating.dto.LogoutResponse;
import com.mxdot.movie_book_rating.dto.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    LoginResponse login(String username, String password);

    RegistrationResponse register(String email, String username, String password);

    LogoutResponse logout(HttpServletRequest request, HttpServletResponse response);

    LoginResponse attemptRefresh(String refreshToken);
}
