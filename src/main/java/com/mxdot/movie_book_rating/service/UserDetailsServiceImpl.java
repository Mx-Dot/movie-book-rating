package com.mxdot.movie_book_rating.service;

import com.mxdot.movie_book_rating.data.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected UserDetailsServiceImpl() {}

    /**
     * Loads a user based on the provided username. This method retrieves a user
     * from the underlying data repository and returns its details. If the user
     * is not found, a {@code UsernameNotFoundException} is thrown.
     *
     * @param username the username of the user to be loaded; must not be null.
     * @return the {@code UserDetails} object containing the user's information.
     * @throws UsernameNotFoundException if no user is found for the provided username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
