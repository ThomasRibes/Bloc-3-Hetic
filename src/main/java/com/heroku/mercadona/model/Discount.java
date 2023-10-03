package com.heroku.mercadona.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Integer id = null;

    @NotNull(message = "Rate is compulsory")
    @Positive(message = "Only positive rate are allowed")
    @Digits(integer = 3, fraction = 0, message = "Rate should have 1 to 3 digits and not fractional")
    @Min(value = 1, message = "Minimum rate is 1")
    @Max(value = 99, message = "Maximum rate is 99")
    @Column(nullable = false, length = 3)
    private Integer rate;

    @NotNull(message = "Start date is compulsory")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "End date is compulsory")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(nullable = false)
    @Getter(AccessLevel.NONE)
    private boolean is_active;

    @ManyToOne
    private Product product;

    public boolean getIs_active() {
        return is_active;
    }

}
