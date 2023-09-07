package com.heroku.mercadona.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length=3)
    private Integer rate;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date startDate;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endDate;
    
    @Column(nullable = false)
    private boolean is_active;

}
