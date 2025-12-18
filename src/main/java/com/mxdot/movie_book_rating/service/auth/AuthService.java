package com.mxdot.movie_book_rating.service.auth;

import com.mxdot.movie_book_rating.dto.LoginResponse;

import javax.crypto.SecretKey;
import java.net.HttpCookie;

public interface AuthService {

    LoginResponse login(String username, String password);

    LoginResponse register(String username, String password);
}
