package com.heroku.mercadona.controller;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.service.DiscountService;
import com.heroku.mercadona.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiscountController {

    private DiscountService discountService;
    private ProductService productService;

    public DiscountController(ProductService productService, DiscountService discountService) {
        this.productService = productService;
        this.discountService = discountService;
    }

    @GetMapping("/admin/product/{id}/discount/new")
    public String createDiscountForm(@PathVariable("id") Integer id, Model model) {
        Discount discount = new Discount();
        Product product = new Product();
        product = this.productService.getProductById(id);
        List<Discount> productDiscountList = product.getDiscounts();
        model.addAttribute("productDiscountList", productDiscountList);
        model.addAttribute("product", product);
        model.addAttribute("discount", discount);
        return "createDiscount";
    }

    @PostMapping("/admin/product/{id}/discount/add")
    public String addDiscount(@PathVariable("id") Integer id,@Valid Discount discount, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Product product = new Product();
            product = this.productService.getProductById(id);
            List<Discount> productDiscountList = product.getDiscounts();
            model.addAttribute("productDiscountList", productDiscountList);
            model.addAttribute("product", product);
            return "createDiscount";
        }
        Product product = this.productService.getProductById(id);
        List<Discount> discountList = new ArrayList<>();
        discountList.add(discount);
        product.setDiscounts(discountList);        
        this.productService.saveProduct(product);
        return "redirect:/admin";
    }
}
