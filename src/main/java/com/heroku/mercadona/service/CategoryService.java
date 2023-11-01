
package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Category;
import java.util.List;


public interface CategoryService {
    List<Category> getAllCategories();
    Category saveCategory(Category category);
    void deleteCategoryById(Integer id);
    public Category getCategoryById(Integer id);
}
