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
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(nullable = false)
    private AdminState state;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Builder.Default
    private LocalDate created_at = LocalDate.now();

    @Column(nullable = false)
    @Builder.Default
    private boolean is_active = true;

    @NotNull(message = "Role is compulsory")
    @Column(nullable = false)
    @Builder.Default
    private String role = "ADMIN";

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setAdmin(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setAdmin(null);
    }
}
