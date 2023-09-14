package com.heroku.mercadona;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repo;

    public Integer getLastInsertedProductId() {
        Iterable<Product> products = repo.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(product);
        }
        int index = productList.size() - 1;
        Product lastInsertedProduct = productList.get(index);

        return lastInsertedProduct.getId();
    }

    @Test
    public void testListAllProducts() {
        Iterable<Product> products = repo.findAll();

        Assertions.assertThat(products).hasSizeGreaterThan(0);

        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setDescription("product test");
        product.setIs_active(true);
        product.setLabel("tested product");
        product.setPrice(50.0);
        product.setUrl("product test url");

        Product savedProduct = repo.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testUpdate() {
        Optional<Product> optionalProduct = repo.findById(getLastInsertedProductId());
        Product product = optionalProduct.get();
        product.setDescription("updated description");

        repo.save(product);

        Product updatedProduct = repo.findById(getLastInsertedProductId()).get();
        Assertions.assertThat(updatedProduct.getDescription()).isEqualTo("updated description");
    }

    @Test
    public void testGet() {
        Optional<Product> optionalProduct = repo.findById(getLastInsertedProductId());
        Product product = optionalProduct.get();

        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

    @Test
    public void testDelete() {
        int id = getLastInsertedProductId();
        repo.deleteById(id);
        Optional<Product> optionalProduct = repo.findById(id);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
}
