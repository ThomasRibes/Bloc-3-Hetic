package com.heroku.mercadona.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private boolean is_active = true;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Admin admin;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discount> discounts = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("id=").append(id);
        sb.append(", label=").append(label);
        sb.append(", description=").append(description);
        sb.append(", price=").append(price);
        sb.append(", discountPrice=").append(discountPrice);
        sb.append(", url=").append(url);
        sb.append(", is_active=").append(is_active);
        sb.append(", category=").append(category);
        sb.append(", admin=").append(admin);
        sb.append(", discounts=").append(discounts);
        sb.append('}');
        return sb.toString();
    }


        public void addDiscount(Discount discount) {
        discounts.add(discount);
        discount.setProduct(this);
    }

    public void removeDiscount(Discount discount) {
        discounts.remove(discount);
        discount.setProduct(null);
    }

    
}
