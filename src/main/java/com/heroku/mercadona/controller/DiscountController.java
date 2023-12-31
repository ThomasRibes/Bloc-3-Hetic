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

    @GetMapping("/admin/product/{idProduct}/discount/new")
    public String createDiscountForm(@PathVariable("idProduct") Integer idProduct, Model model) {
        Discount discount = new Discount();
        Product product = this.productService.getProductById(idProduct);
        List<Discount> productDiscountList = product.getDiscounts();
        model.addAttribute("productDiscountList", productDiscountList);
        model.addAttribute("product", product);
        model.addAttribute("discount", discount);
        return "manageDiscounts";
    }

    @PostMapping("/admin/product/{idProduct}/discount/add")
    public String addDiscount(@PathVariable("idProduct") Integer idProduct, @Valid Discount discount, BindingResult result, Model model) {

        if (result.hasErrors()) {
            Product product = this.productService.getProductById(idProduct);
            List<Discount> productDiscountList = product.getDiscounts();
            model.addAttribute("productDiscountList", productDiscountList);
            model.addAttribute("product", product);
            return "manageDiscounts";
        }
        if (discountService.checkDiscountDatesCompatibility(discount)) {
            Product product = this.productService.getProductById(idProduct);
            product.addDiscount(discount);
            this.productService.saveProduct(product);
        }
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
