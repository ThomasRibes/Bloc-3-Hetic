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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

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
        return "createProduct";
    }
    
    @PostMapping("/admin/product/add")
    public String addProduct(@Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            return "createProduct";
        }
        this.productRepository.save(product);
        System.out.println(product);
        return "redirect:/admin";    
    }
    
    @GetMapping("/admin/product/edit/{id}")
    public String updateProductForm(@PathVariable("id") Integer id, Model model){
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("listCategories", listCategories);
        Product product = this.productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid product id : " + id));
        model.addAttribute("product",product);
        return "updateProduct";    
    }
    
    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            product.setId(id);
            return "updateProduct";
        }
        productRepository.save(product);
        return "redirect:/admin";
    }
    
    @GetMapping("admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model){
        Product product = this.productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid product id : " + id));
        this.productRepository.delete(product);
        return "redirect:/admin";
    }
    

}
