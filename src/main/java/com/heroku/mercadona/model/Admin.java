package com.heroku.mercadona.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name is compulsory")
    @Size(min = 3, max = 50, message = "Name should be a minimum of 3 characters and a maximum of 30")
    @Column(nullable = false, length = 30)
    private String name;

    @NotNull(message = "Email is compulsory")
    @Email(message = "Email Address is not a valid format")
    @Column(nullable = false, unique = true, length = 250)
    private String email;

    @NotNull(message = "Password is compulsory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 16}$",
            message = "Password should contains a minimum of: 1 lower case , 1 upper case, 1 digit, 1 special character, no white spaces and at least 8 and at most 20 characters")
    @Column(nullable = false, unique = true, length = 250)
    private String password;

    @NotNull(message = "State is compulsory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AdminState state;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Builder.Default
    private LocalDate created_at = LocalDate.now();

    @Column(nullable = false)
    @Builder.Default
    private boolean is_active = true;
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Product> products;

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

    public AdminState getState() {
        return state;
    }

    public void setState(AdminState state) {
        this.state = state;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Admin{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", state=").append(state);
        sb.append(", created_at=").append(created_at);
        sb.append(", is_active=").append(is_active);
        sb.append('}');
        return sb.toString();
    }

}
