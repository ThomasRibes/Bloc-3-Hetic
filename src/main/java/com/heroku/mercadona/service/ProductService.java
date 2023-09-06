
package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;
    
    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
    }
}
