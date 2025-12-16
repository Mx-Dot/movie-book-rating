package com.mxdot.movie_book_rating.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.mxdot.movie_book_rating.domain")
@EnableJpaRepositories("com.mxdot.movie_book_rating.repos")
@EnableTransactionManagement
public class DomainConfig {
}
