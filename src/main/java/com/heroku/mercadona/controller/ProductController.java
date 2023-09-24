package com.heroku.mercadona.controller;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.service.CategoryService;
import com.heroku.mercadona.service.DiscountService;
import com.heroku.mercadona.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final DiscountService discountService;

    public ProductController(ProductService productService, CategoryService categoryService, DiscountService discountService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.discountService = discountService;
    }

    @GetMapping("/catalog")
    public String showProductList(Model model) {
        List<Product> listProducts = productService.getAllProducts();
        List<Discount> listDiscounts = discountService.getAllDiscounts();
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listDiscounts", listDiscounts);
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
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> listCategories = categoryService.getAllCategories();
            model.addAttribute("listCategories", listCategories);
            return "createProduct";
        }
        this.productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String updateProductForm(@PathVariable("id") Integer id, Model model) {
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("listCategories", listCategories);
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            List<Category> listCategories = categoryService.getAllCategories();
            model.addAttribute("listCategories", listCategories);
            return "updateProduct";
        }
        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        this.productService.deleteProductById(id);
        return "redirect:/admin";
    }

    //activate
    @GetMapping("/admin/product/activate/{id}")
    public String activateProduct(@PathVariable("id") Integer id, Model model) {
        Product product = this.productService.getProductById(id);
        product.setIs_active(true);
        this.productService.saveProduct(product);
        return "redirect:/admin";
    }

    //disactivate
    @GetMapping("/admin/product/disactivate/{id}")
    public String disactivateProduct(@PathVariable("id") Integer id, Model model) {
        Product product = this.productService.getProductById(id);
        product.setIs_active(false);
        this.productService.saveProduct(product);
        return "redirect:/admin";
    }

}
