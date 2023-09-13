package com.heroku.mercadona;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
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

    @Test
    public void testAddNew() {
        Product product = new Product();
        product.setDescription("product test");
        product.setIs_active(true);
        product.setLabel("tested product");
        product.setPrice(50.0);
        product.setUrl("product test url2");

        Product savedProduct = repo.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Product> products = repo.findAll();

        Assertions.assertThat(products).hasSizeGreaterThan(0);

        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void testUpdate() {
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();
        product.setDescription("updated description");

        repo.save(product);

        Product updatedProduct = repo.findById(productId).get();
        Assertions.assertThat(updatedProduct.getDescription()).isEqualTo("updated description");
    }

    @Test
    public void testGet() {
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();

        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

//    @Test
//    public void testDelete() {
//        
//        Integer productId = 2;
//        repo.deleteById(productId);
//
//        Optional<Product> optionalProduct = repo.findById(productId);
//        Assertions.assertThat(optionalProduct).isNotPresent();
//    }

}
