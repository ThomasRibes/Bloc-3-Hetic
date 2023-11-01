package com.heroku.mercadona.controller;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.service.CategoryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category/new")
    public String createCategoryForm(Model model) {
        Category category = new Category();
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", category);
        return "manageCategories";
    }

    @PostMapping("/admin/category/add")
    public String addDiscount(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categoryList = categoryService.getAllCategories();
            model.addAttribute("categoryList", categoryList);
            return "manageCategories";
        }
        this.categoryService.saveCategory(category);
        return "redirect:/admin/category/new";
    }
    
    @GetMapping("/admin/category/edit/{id}")
    public String updateCategoryForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "updateCategory";
    }

    @PostMapping("/admin/category/update/{id}")
    public String updateCategory(@PathVariable("id") Integer id, @Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "updateCategory";
        }
        categoryService.saveCategory(category);
        return "redirect:/admin/category/new";
    }    

    @GetMapping("admin/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id, Model model) {
        this.categoryService.deleteCategoryById(id);
        return "redirect:/admin/category/new";
    }

}