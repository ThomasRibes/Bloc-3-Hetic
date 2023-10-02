package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.repository.CategoryRepository;
import com.heroku.mercadona.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

}
