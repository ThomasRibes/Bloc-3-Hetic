package com.heroku.mercadona.Service;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void integrationTestCategoryRepositoryListAllCategories() {
        //Arrange
        //Act
        Iterable<Category> categories = categoryService.getAllCategories();

        long size = categories.spliterator().getExactSizeIfKnown();

        //Assert
        Assertions.assertTrue(size > 0, "Category list exists");
    }

}