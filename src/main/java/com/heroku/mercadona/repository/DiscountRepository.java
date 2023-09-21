package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Discount;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, Integer>{
    
}
