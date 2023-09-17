
package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Product;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProductById(Integer id);
    void deleteProductById(Integer id);
    
}
