
package com.heroku.mercadona.controller;


import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @Autowired private ProductService service;
    
    @GetMapping("/catalog")
    public String showProductList(Model model){
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts",listProducts);
        
        return "showProducts";
    }
    
    
}