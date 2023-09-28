
package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Product;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    boolean checkIfParamMatchACategory(Integer categoryId);
    boolean checkIfParamMatchNull(Integer categoryId);
    List<Product> getProductListByCategory(Integer categoryId);
    void saveProduct(Product product);
    Product getProductById(Integer id);
    void deleteProductById(Integer id);
    Integer getLastInsertedProductId();
    void updateDiscountPrice(List<Product> productList);
}