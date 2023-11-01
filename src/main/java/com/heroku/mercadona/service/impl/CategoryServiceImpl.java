package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.repository.CategoryRepository;
import com.heroku.mercadona.service.CategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = null;
        if (optional.isPresent()) {
            category = optional.get();
        } else {
            throw new RuntimeException("Category not found for id : " + id);
        }
        return category;
    }

}
