package com.heroku.mercadona.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @Column(nullable = false, unique = true, length = 250)
    private String email;

    @Column(nullable = false, unique = true, length = 250)
    private String password;

    @Column(nullable = false, length = 10)
    private String role;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate created_at = LocalDate.now();

    @Column(nullable = false)
    private boolean is_active = true;
    
    @Column(nullable = false)
    private boolean first_connexion = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public boolean setIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isFirst_connexion() {
        return first_connexion;
    }

    public void setFirst_connexion(boolean first_connexion) {
        this.first_connexion = first_connexion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Admin{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", role=").append(role);
        sb.append(", created_at=").append(created_at);
        sb.append(", is_active=").append(is_active);
        sb.append(", first_connexion=").append(first_connexion);
        sb.append('}');
        return sb.toString();
    }
    
    

}
