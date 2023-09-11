package com.heroku.mercadona.controller;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.service.CategoryService;
import com.heroku.mercadona.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/catalog")
    public String showProductList(Model model) {
        List<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "showProducts";
    }

    @GetMapping("/admin")
    public String showAdminProductList(Model model) {
        List<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "showAdminProducts";
    }

    @GetMapping("/admin/product/new")
    public String createProductForm(Model model) {
        Product product = new Product();
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("product", product);
        model.addAttribute("listCategories", listCategories);
        System.out.println(listCategories);
        return "createProduct";
    }

}
