package com.heroku.mercadona;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryTestRepository;

    @Test
    public void integrationTestListAllCategories() {
        Iterable<Category> categories = categoryTestRepository.findAll();

        Assertions.assertThat(categories).hasSizeGreaterThan(0);

        for (Category category : categories) {
            System.out.println(category);
        }
    }

}
