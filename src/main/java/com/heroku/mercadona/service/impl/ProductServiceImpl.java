package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
import com.heroku.mercadona.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
}
