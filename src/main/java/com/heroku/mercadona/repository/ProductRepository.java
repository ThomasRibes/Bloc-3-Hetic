
package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Integer>{
    
    @Query("SELECT p FROM Product p WHERE p.is_active = true")
    public List<Product> findAllActiveProducts();
    
    Product findTopByOrderByIdDesc();
    
}