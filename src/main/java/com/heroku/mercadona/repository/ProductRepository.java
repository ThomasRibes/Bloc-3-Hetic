
package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer>{    
}
