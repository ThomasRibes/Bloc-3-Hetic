package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Admin findByName(String name);

    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    public Admin findAdminByEmail(String email);
}
