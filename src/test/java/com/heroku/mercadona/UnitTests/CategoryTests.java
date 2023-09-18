package com.heroku.mercadona.UnitTests;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTests {

    @Test
    public void unitTestCategorySetIdAndGetId() {
        //Arrange
        Category category = new Category();
        Integer expected = 0;
        //Act
        category.setId(0);
        Integer actual = category.getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestCategorySetLabelAndGetLabel() {
        //Arrange
        Category category = new Category();
        String expected = "label";
        //Act
        category.setLabel("label");
        String actual = category.getLabel();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestCategorySetProductsAndGetProducts() {
        //Arrange
        Product product = new Product();
        product.setId(333);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Category category = new Category();
        Integer expected = 333;
        //Act
        category.setProducts(productList);
        Integer actual = category.getProducts().get(0).getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestCategoryToString() {
        //Arrange
        Category category = new Category();
        category.setId(1);
        category.setLabel("tested category");
        String expected = "Category{id=1, label=tested category}";
        //Act
        String actual = category.toString();
        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
