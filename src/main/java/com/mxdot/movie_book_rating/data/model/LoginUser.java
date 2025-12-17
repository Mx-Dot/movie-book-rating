package com.mxdot.movie_book_rating.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "LOGIN_USER")
public class LoginUser implements UserDetails {

    /**
     * Represents the unique identifier for the LoginUser entity.
     * Mapped to the primary key of the associated database table.
     */
    @Id
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    /**
     * Retrieves the collection of authorities granted to the user.
     * These authorities define the access permissions for the user.
     *
     * @return a collection of granted authorities for the user.
     *         In this implementation, an empty collection is returned.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    /**
     * Retrieves the username associated with this user.
     *
     * @return the username of the user.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password associated with this user.
     *
     * @return the password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }
}
