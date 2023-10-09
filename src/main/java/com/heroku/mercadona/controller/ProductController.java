package com.heroku.mercadona.controller;

import com.heroku.mercadona.model.Admin;
import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.service.AdminService;
import com.heroku.mercadona.service.CategoryService;
import com.heroku.mercadona.service.ProductService;
import com.heroku.mercadona.service.StorageService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final AdminService adminService;
    private final StorageService storageService;

    public ProductController(ProductService productService, CategoryService categoryService, AdminService adminService, StorageService storageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.adminService = adminService;
        this.storageService = storageService;
    }

    @GetMapping("/catalog")
    public String showCurrentProductList(Model model) {
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("listCategories", listCategories);
        List<Product> listProducts = productService.getAllActiveProducts();
        productService.updateDiscountPrice(listProducts);
        List<Product> currentProductList = productService.getAllActiveProducts();
        model.addAttribute("currentProductList", currentProductList);
        return "showProducts";
    }

    @GetMapping("/catalog/filter")
    public String showCurrentFilteredProductList(@RequestParam(required = false) Integer categoryId, Model model) {
        List<Product> listProducts = productService.getAllActiveProducts();
        productService.updateDiscountPrice(listProducts);
        if (productService.checkIfParamMatchNull(categoryId) || productService.checkIfParamMatchZero(categoryId)) {
            List<Product> currentProductList = productService.getAllActiveProducts();
            model.addAttribute("currentProductList", currentProductList);
        }
        if (productService.checkIfParamMatchACategory(categoryId)) {
            List<Product> currentProductList = productService.getActiveProductListByCategory(categoryId);
            model.addAttribute("currentProductList", currentProductList);
        }
        return "frag/filteredProducts :: ourFilteredProducts";
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
        model.addAttribute("product", product);
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("listCategories", listCategories);
        return "createProduct";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@RequestParam(value = "file") MultipartFile file, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> listCategories = categoryService.getAllCategories();
            model.addAttribute("listCategories", listCategories);
            return "createProduct";
        }
        //check if file exists and is valid or send exception
        String fileName = storageService.fileNameGen(file);
        String url = storageService.urlGen(fileName);
        product.setUrl(url);
        storageService.saveFile(file, fileName);
        String username = this.adminService.getAuthenticatedAdminName();
        Admin admin = this.adminService.getAdminByName(username);
        admin.addProduct(product);
        this.adminService.saveAdmin(admin);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/edit/{idProduct}")
    public String updateProductForm(@PathVariable("idProduct") Integer idProduct, Model model) {
        List<Category> listCategory = categoryService.getAllCategories();
        model.addAttribute("listCategory", listCategory);
        Product product = this.productService.getProductById(idProduct);
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

    //Edit product picture
    @GetMapping("/admin/product/{id}/edit/picture")
    public String updateProductPictureForm(@PathVariable("id") Integer id, Model model) {
        //check if file exists and is valid or send exception
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "updateProductPicture";
    }

    //Update product picture
    @PostMapping("/admin/product/{id}/update/picture")
    public String updateProductPicture(@RequestParam(value = "file") MultipartFile file, @PathVariable("id") Integer id, Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "updateProductPicture";
        }
        //check if file exists and is valid or send exception        
        Product productFinal = this.productService.getProductById(id);
        String urlToDelete = productFinal.getUrl();
        String fileNameToDelete = storageService.urlToFileName(urlToDelete);
        storageService.deleteFile(fileNameToDelete);
        String fileName = storageService.fileNameGen(file);
        String url = storageService.urlGen(fileName);
        storageService.saveFile(file, fileName);
        productFinal.setUrl(url);
        this.productService.saveProduct(productFinal);

        return "redirect:/admin";
    }

    @GetMapping("admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        Product product = this.productService.getProductById(id);
        String url = product.getUrl();
        String fileName = storageService.urlToFileName(url);
        storageService.deleteFile(fileName);
        this.productService.deleteProductById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/activate/{id}")
    public String activateProduct(@PathVariable("id") Integer id, Model model) {
        Product product = this.productService.getProductById(id);
        product.setIs_active(true);
        this.productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/disactivate/{id}")
    public String disactivateProduct(@PathVariable("id") Integer id, Model model) {
        Product product = this.productService.getProductById(id);
        product.setIs_active(false);
        this.productService.saveProduct(product);
        return "redirect:/admin";
    }

}
