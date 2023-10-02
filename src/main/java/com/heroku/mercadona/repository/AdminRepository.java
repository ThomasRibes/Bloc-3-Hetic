package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findByName(String name);
}
