package com.Authentication.Cafe_Demo.Support.repository;

import com.Authentication.Cafe_Demo.Support.model.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepo extends JpaRepository<Support, Long> {
}
