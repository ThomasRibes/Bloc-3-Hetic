
package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Product;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getAllActiveProducts();
    boolean checkIfParamMatchACategory(Integer categoryId);
    boolean checkIfParamMatchNull(Integer categoryId);
    boolean checkIfParamMatchZero(Integer categoryId);
    List<Product> getActiveProductListByCategory(Integer categoryId);
    void saveProduct(Product product);
    Product getProductById(Integer id);
    void deleteProductById(Integer id);
    Integer getLastInsertedProductId();
    void updateDiscountPrice(List<Product> productList);
}