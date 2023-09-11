package com.heroku.mercadona.controller;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
import com.heroku.mercadona.service.CategoryService;
import com.heroku.mercadona.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private ProductRepository productRepository;

    public ProductController(ProductService productService, CategoryService categoryService, ProductRepository productRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
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
    
    @PostMapping("/admin/product/add")
    public String addProduct(@Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            return "createProduct";
        }
        this.productRepository.save(product);
        System.out.println(product);
        return "showProducts";    
    }
            

}
