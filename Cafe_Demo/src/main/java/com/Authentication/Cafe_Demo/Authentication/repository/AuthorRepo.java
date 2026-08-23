package com.Authentication.Cafe_Demo.Authentication.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Authentication.Cafe_Demo.Authentication.model.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
//    find by email in database
    Optional<Author> findByEmail(String email);
//    check by email in database
    boolean existsByEmail(String email);
}
