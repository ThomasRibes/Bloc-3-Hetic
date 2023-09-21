package com.heroku.mercadona.Service;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void integrationTestProductRepositoryAddProduct() {
        //Arrange
        Product product = new Product();
        product.setDescription("test add product");
        product.setLabel("tested product");
        product.setPrice(10.0);
        //Act
        productService.saveProduct(product);
        Product productAdded = productService.getProductById(productService.getLastInsertedProductId());
        //Assert
        Assertions.assertNotNull(productAdded, "Product should not be null");
        Assertions.assertEquals("test add product",productAdded.getDescription());
        productService.deleteProductById(productAdded.getId());
    }

    @Test
    public void integrationTestProductRepositoryGetProductById() {
        //Arrange
        Product product1 = new Product();
        product1.setDescription("test get product by id 1");
        product1.setLabel("product1");
        product1.setPrice(20.0);
        Product product2 = new Product();
        product2.setDescription("test get product by id 2");
        product2.setLabel("product2");
        product2.setPrice(30.0);
        //Act
        productService.saveProduct(product1);
        Integer firstProductId = productService.getLastInsertedProductId();
        productService.saveProduct(product2);
        Product productAdded = productService.getProductById(firstProductId + 1);
        //Assert
        Assertions.assertEquals(productAdded.getLabel(), "product2");
        productService.deleteProductById(firstProductId);
        productService.deleteProductById(firstProductId + 1);
    }

    @Test
    public void integrationTestProductRepositoryListAllProducts() {
        //Arrange
        //Act
        Iterable<Product> products = productService.getAllProducts();
        long size = products.spliterator().getExactSizeIfKnown();
        //Assert
        Assertions.assertTrue(size > 0, "Product list exists");
    }

    @Test
    public void integrationTestProductRepositoryUpdate() {
        //Arrange
        Product product = new Product();
        product.setDescription("test update product");
        product.setLabel("tested product");
        product.setPrice(40.0);
        productService.saveProduct(product);
        int id = productService.getLastInsertedProductId();
        Product productAdded = productService.getProductById(id);
        String expected = "updated description";
        productAdded.setDescription(expected);
        //Act
        productService.saveProduct(productAdded);
        Product updatedProduct = productService.getProductById(id);
        String actual = updatedProduct.getDescription();
        //Assert
        Assertions.assertEquals(expected, actual);
        productService.deleteProductById(id);
    }

    @Test
    public void integrationTestProductRepositoryDeleteProductById() {
        //Arrange
        Product product = new Product();
        product.setDescription("test delete product");
        product.setLabel("tested product");
        product.setPrice(50.0);
        productService.saveProduct(product);
        int id = productService.getLastInsertedProductId();
        //Act
        productService.deleteProductById(id);
        int idAfterDeleteTest = productService.getLastInsertedProductId();
        //Assert
        Assertions.assertNotEquals(id, idAfterDeleteTest, "Failure , id are equals");
    }
}
