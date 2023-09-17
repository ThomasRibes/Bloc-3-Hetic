package com.heroku.mercadona;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.service.ProductService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProductRepositoryTests {

    @Autowired
    private ProductService productService;

    @Test
    @Order(1)
    public void integrationTestAddProduct() {
        //Arrange
        Product product = new Product();
        product.setDescription("product test");
        product.setIs_active(true);
        product.setLabel("tested product");
        product.setPrice(50.0);
        product.setUrl("product test url");

        //Act
        productService.saveProduct(product);

        //Assert
        Assertions.assertNotNull(product, "Product should not be null");
        Assertions.assertTrue(product.getId() > 0, "Product id should be greater than 0");
    }

    @Test
    @Order(2)
    public void integrationTestGetProductById() {
        //Arrange
        //Act
        Product product = productService.getProductById(productService.getLastInsertedProductId());

        //Assert
        Assertions.assertNotNull(product, "Product should not be null");
    }

    @Test
    @Order(3)
    public void integrationTestListAllProducts() {
        //Arrange
        //Act
        Iterable<Product> products = productService.getAllProducts();

        long size = products.spliterator().getExactSizeIfKnown();

        //Assert
        Assertions.assertTrue(size > 0, "Product list exists");
    }

    @Test
    @Order(4)
    public void integrationTestUpdate() {
        //Arrange
        int id = productService.getLastInsertedProductId();
        Product product = productService.getProductById(id);
        String expected = "updated description";
        product.setDescription(expected);

        //Act
        productService.saveProduct(product);

        Product updatedProduct = productService.getProductById(id);
        String actual = updatedProduct.getDescription();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    public void integrationTestDeleteProductById() {
        //Arrange
        int id = productService.getLastInsertedProductId();

        //Act
        productService.deleteProductById(id);
        int idAfterDeleteTest = productService.getLastInsertedProductId();

        //Assert
        Assertions.assertNotEquals(id, idAfterDeleteTest, "Failure , id are equals");
    }
}
