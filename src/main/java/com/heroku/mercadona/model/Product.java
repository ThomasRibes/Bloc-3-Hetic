package com.heroku.mercadona.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")

@NamedNativeQuery(
        name = "getProductListByCategory",
        query = "SELECT * "
        + "FROM products"
        + " WHERE category_id = ?",
        resultClass = Product.class
)

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Label is compulsory")
    @Size(min = 3, max = 50, message = "Label should be a minimum of 3 characters and a maximum of 50")
    @Pattern(regexp = "^[A-Za-z0-9\\s^'!.?:()_-]*$", message = "Label has invalid characters, only apostrophes, alphanumerics and _-.!?:() are allowed")
    @Column(nullable = false, unique = true, length = 50)
    private String label;

    @NotNull(message = "Description is compulsory")
    @Pattern(regexp = "^[A-Za-z0-9\\s^'!.?:()_-]*$", message = "Description has invalid characters, only apostrophes, alphanumerics and _-.!?:() are allowed")
    @Size(min = 10, max = 500, message = "Description should be a minimum of 10 characters and a maximum of 500")
    @Column(nullable = false, length = 500)
    private String description;

    @NotNull(message = "Price is compulsory")
    @Positive(message = "Price can only be positive")
    @DecimalMax(value = "10000.0", message = "Maximum price is 10000.00€")
    @DecimalMin(value = "0.01", message = "Minimum price is 0.01€")
    @Column(nullable = false, length = 8)
    private Double price;

    @Positive(message = "Discount price can only be positive")
    @DecimalMax(value = "10000.0", message = "Maximum discount price is 10000.00€")
    @DecimalMin(value = "0.01", message = "Minimum discount price is 0.01€")
    @Column(length = 8)
    private Double discountPrice;

//  To implements when implementing urls 
//    @NotNull(message = "Url is compulsory")
//    @Size(min = 10, max = 500, message = "URL should be a minimum of 10 characters and a maximum of 500")
    @Column(/*nullable = false,*/length = 500)
    private String url;

    @Column(nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private boolean is_active = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Discount> discounts = new ArrayList<>();

    public void addDiscount(Discount discount) {
        discounts.add(discount);
        discount.setProduct(this);
    }

    public void removeDiscount(Discount discount) {
        discounts.remove(discount);
        discount.setProduct(null);
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

}
