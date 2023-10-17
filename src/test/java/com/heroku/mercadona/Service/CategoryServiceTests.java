package com.heroku.mercadona.Service;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.repository.CategoryRepository;
import com.heroku.mercadona.service.impl.CategoryServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTests {

    @Mock
    private CategoryRepository CategoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category1;

    @BeforeEach
    public void setup() {
        category1 = new Category();
        category1.setId(1);
        category1.setLabel("firstCategory");
    }

    @DisplayName("JUnit test for getAllCategories method")
    @Test
    public void givenCategoriesList_whenGetAllCategories_thenReturnCategoriesList() {
        //given
        Category category2 = new Category();
        category2.setId(2);
        category2.setLabel("test");

        given(CategoryRepository.findAll()).willReturn(List.of(category1, category2));
        //when
        List<Category> categoryList = categoryService.getAllCategories();
        
        //then
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);
    }

}
