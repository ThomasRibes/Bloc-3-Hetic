package com.heroku.mercadona.controller;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;
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
public class DiscountController {

    private final DiscountService discountService;
    private final ProductService productService;

    public DiscountController(ProductService productService, DiscountService discountService) {
        this.productService = productService;
        this.discountService = discountService;
    }

    @GetMapping("/admin/product/{id}/discount/new")
    public String createDiscountForm(@PathVariable("id") Integer id, Model model) {
        Discount discount = new Discount();
        Product product = this.productService.getProductById(id);
        List<Discount> productDiscountList = product.getDiscounts();
        model.addAttribute("productDiscountList", productDiscountList);
        model.addAttribute("product", product);
        model.addAttribute("discount", discount);
        return "manageDiscount";
    }

    @PostMapping("/admin/product/{id}/discount/add")
    public String addDiscount(@PathVariable("id") Integer id, @Valid Discount discount, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Product product = this.productService.getProductById(id);
            List<Discount> productDiscountList = product.getDiscounts();
            model.addAttribute("productDiscountList", productDiscountList);
            model.addAttribute("product", product);
            return "manageDiscount";
        }
        Product product = this.productService.getProductById(id);
        product.addDiscount(discount);
        this.productService.saveProduct(product);
        return "redirect:/admin/product/{id}/discount/new";
    }

    @GetMapping("/admin/product/{idProduct}/discount/edit/{id}")
    public String updateDiscountForm(@PathVariable("idProduct") Integer idProduct, @PathVariable("id") Integer id, Model model) {
        Discount discount = this.discountService.getDiscountById(id);
        Product product = this.productService.getProductById(idProduct);
        List<Discount> productDiscountList = product.getDiscounts();
        model.addAttribute("discount", discount);
        model.addAttribute("productDiscountList", productDiscountList);
        model.addAttribute("product", product);
        return "manageDiscount";
    }

    @PostMapping("/admin/product/{idProduct}/discount/update/{id}")
    public String updateProduct(@PathVariable("idProduct") Integer idProduct, @PathVariable("id") Integer id, @Valid Discount discount, BindingResult result, Model model) {
        if (result.hasErrors()) {
            discount.setId(id);
            return "manageDiscount";
        }
        discountService.saveDiscount(discount);
        return "redirect:/admin/product/{idProduct}/discount/new";
    }

    @GetMapping("/admin/product/{idProduct}/discount/activate/{id}")
    public String activateDiscount(@PathVariable("idProduct") Integer idProduct, @PathVariable("id") Integer id, Model model) {
        Discount discount = this.discountService.getDiscountById(id);
        discount.setIs_active(true);
        this.discountService.saveDiscount(discount);
        return "redirect:/admin/product/{idProduct}/discount/new";
    }

    @GetMapping("/admin/product/{idProduct}/discount/disactivate/{id}")
    public String disactivateDiscount(@PathVariable("idProduct") Integer idProduct, @PathVariable("id") Integer id, Model model) {
        Discount discount = this.discountService.getDiscountById(id);
        discount.setIs_active(false);
        this.discountService.saveDiscount(discount);
        return "redirect:/admin/product/{idProduct}/discount/new";
    }
}
