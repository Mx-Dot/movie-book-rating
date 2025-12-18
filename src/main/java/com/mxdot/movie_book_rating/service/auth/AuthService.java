package com.mxdot.movie_book_rating.service.auth;

import com.mxdot.movie_book_rating.dto.LoginResponse;
import com.mxdot.movie_book_rating.dto.LogoutResponse;
import com.mxdot.movie_book_rating.dto.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    LoginResponse login(String username, String password);

    RegistrationResponse register(String username, String password);

    LogoutResponse logout(HttpServletRequest request);

    LoginResponse attemptRefresh(String refreshToken);
}
