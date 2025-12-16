package com.mxdot.movie_book_rating.data.repository;

import com.mxdot.movie_book_rating.data.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, Long> {

    Optional<LoginUser> findByUsername(String username);
}
