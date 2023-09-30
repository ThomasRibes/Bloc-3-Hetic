package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}