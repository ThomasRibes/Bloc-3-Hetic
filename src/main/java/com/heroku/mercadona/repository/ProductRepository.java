
package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer>{
    
}
