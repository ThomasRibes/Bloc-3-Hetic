package com.heroku.mercadona;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productTestRepository;

    public Integer getLastInsertedProductId() {
        Iterable<Product> products = productTestRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(product);
        }
        int index = productList.size() - 1;
        Product lastInsertedProduct = productList.get(index);

        return lastInsertedProduct.getId();
    }

    @Test
    @Order(1)
    public void integrationTestAddProduct() {
        Product product = new Product();
        product.setDescription("product test");
        product.setIs_active(true);
        product.setLabel("tested product");
        product.setPrice(50.0);
        product.setUrl("product test url");

        Product savedProduct = productTestRepository.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void integrationTestGet() {
        Optional<Product> optionalProduct = productTestRepository.findById(getLastInsertedProductId());
        Product product = optionalProduct.get();

        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

    @Test
    @Order(3)
    public void integrationTestListAllProducts() {
        Iterable<Product> products = productTestRepository.findAll();

        Assertions.assertThat(products).hasSizeGreaterThan(0);

        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    @Order(4)
    public void integrationTestUpdate() {
        Optional<Product> optionalProduct = productTestRepository.findById(getLastInsertedProductId());
        Product product = optionalProduct.get();
        product.setDescription("updated description");

        productTestRepository.save(product);

        Product updatedProduct = productTestRepository.findById(getLastInsertedProductId()).get();
        Assertions.assertThat(updatedProduct.getDescription()).isEqualTo("updated description");
    }

    @Test
    @Order(5)
    public void integrationTestDelete() {
        int id = getLastInsertedProductId();
        productTestRepository.deleteById(id);
        Optional<Product> optionalProduct = productTestRepository.findById(id);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
}
