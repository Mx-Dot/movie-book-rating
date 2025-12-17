package com.mxdot.movie_book_rating.security;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfiguration {

    /**
     * Defines a {@link PasswordEncoder} bean for encoding and verifying passwords.
     * Uses {@link BCryptPasswordEncoder}, which provides a strong hashing algorithm
     * suitable for secure password storage.
     *
     * @return a {@link PasswordEncoder} instance for encoding and validating passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain for the application.
     * The configuration includes session management with stateless sessions, request authorization
     * for specific endpoints, and disabling CSRF protection. Additionally, it configures CORS
     * settings using a dedicated CORS configuration source.
     *
     * @param security an instance of {@link HttpSecurity} used to build the security filter chain.
     * @return a {@link SecurityFilterChain} object that defines the security rules and filters for the application.
     * @throws Exception if an error occurs during the configuration process.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity security) throws Exception {
        return security.sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(("/api/**")).authenticated().anyRequest().permitAll();

                }).csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfig()))
                .build();
    }

    /**
     * Configures a {@link CorsConfigurationSource} to define settings for Cross-Origin Resource Sharing (CORS).
     * The configuration includes allowed origins, methods, headers, and exposed headers,
     * as well as enabling credentials and setting the maximum age for CORS requests.
     *
     * @return a {@link CorsConfigurationSource} instance containing the CORS configuration details.
     */
    private CorsConfigurationSource corsConfig() {
        return new CorsConfigurationSource() {
            @Override
            public @NonNull CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cors = new CorsConfiguration();
                cors.setAllowedOrigins(List.of("http://localhost:3000"));
                cors.setAllowedMethods(Collections.singletonList("*"));
                cors.setAllowCredentials(true);
                cors.setAllowedHeaders(Collections.singletonList("*"));
                cors.setExposedHeaders(List.of("Authorization"));
                cors.setMaxAge(3600L);
                return cors;
            }
        };
    }
}
