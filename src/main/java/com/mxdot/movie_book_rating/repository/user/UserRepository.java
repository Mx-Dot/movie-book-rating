package com.mxdot.movie_book_rating.repository.user;

import com.mxdot.movie_book_rating.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a User entity from the database based on the provided username.
     *
     * @param username the username of the user to be retrieved; must not be null.
     * @return an Optional containing the User if found, otherwise an empty Optional.
     */
    Optional<User> findByUsername(String username);
}
