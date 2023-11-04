package com.heroku.mercadona.Service;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.repository.CategoryRepository;
import com.heroku.mercadona.service.impl.CategoryServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    public void setup() {
        category = new Category();
        category.setId(1);
        category.setLabel("firstCategory");
    }

    @DisplayName("JUnit test for getAllCategories method")
    @Test
    public void givenCategoriesList_whenGetAllCategories_thenReturnCategoriesList() {
        //given
        Category category2 = new Category();
        category2.setId(2);
        category2.setLabel("test");

        given(categoryRepository.findAll()).willReturn(List.of(category, category2));
        //when
        List<Category> categoryList = categoryService.getAllCategories();

        //then
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for saveCategory method")
    @Test
    public void givenCategoryObject_whenSaveCategory_thenReturnCategoryObject() {
        //given
        given(categoryRepository.findById(category.getId()))
                .willReturn(Optional.empty());

        given(categoryRepository.save(category)).willReturn(category);

        //when        
        Category savedCategory = categoryService.saveCategory(category);

        //then
        assertThat(savedCategory).isNotNull();
    }

    @DisplayName("JUnit test for getCategoryById method")
    @Test
    public void givenCategoryId_whenGetCategoryById_thenReturnCategoryObject() {
        // given
        given(categoryRepository.findById(1)).willReturn(Optional.of(category));

        // when
        Category savedCategory = categoryService.getCategoryById(category.getId());

        //then
        assertThat(savedCategory).isNotNull();
    }

    @DisplayName("JUnit test for deleteCategoryById method")
    @Test
    public void givenCategoryId_whenDeleteCategoryById_thenNothing() {
        // given
        Integer categoryId = 1;

        willDoNothing().given(categoryRepository).deleteById(categoryId);

        // when
        categoryService.deleteCategoryById(categoryId);

        //then
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

}
