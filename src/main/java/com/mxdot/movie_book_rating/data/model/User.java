package com.mxdot.movie_book_rating.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LOGIN_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * Represents the unique identifier for the LoginUser entity.
     * Mapped to the primary key of the associated database table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

}
