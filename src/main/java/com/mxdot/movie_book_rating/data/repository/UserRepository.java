package com.mxdot.movie_book_rating.data.repository;

import com.mxdot.movie_book_rating.data.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, Long> {

    /**
     * Retrieves a LoginUser entity from the database based on the provided username.
     *
     * @param username the username of the user to be retrieved; must not be null.
     * @return an Optional containing the LoginUser if found, otherwise an empty Optional.
     */
    Optional<LoginUser> findByUsername(String username);
}
