package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
import com.heroku.mercadona.service.CategoryService;
import com.heroku.mercadona.service.DiscountService;
import com.heroku.mercadona.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final DiscountService discountService;

    @PersistenceContext
    private EntityManager manager;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, DiscountService discountService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.discountService = discountService;
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public boolean checkIfParamMatchACategory(Integer categoryId) {
        Boolean paramMatchCategory = false;
        List<Category> listOfCategories = categoryService.getAllCategories();
        if (listOfCategories.stream().anyMatch(c -> c.getLabel().equals(categoryId))) {
            paramMatchCategory = true;
        }
        return paramMatchCategory;
    }

    @Override
    public boolean checkIfParamMatchNull(Integer categoryId) {
        Boolean paramMatchNull = false;
        if (categoryId == null) {
            paramMatchNull = true;
        }
        return paramMatchNull;
    }

    @Override
    public List<Product> getProductListByCategory(Integer categoryId) {
        List<Product> productListByCategory = manager.createNamedQuery("getProductListByCategory", Product.class)
                .setParameter(1, categoryId)
                .getResultList();
        return productListByCategory;
    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Product not found for id : " + id);
        }
        return product;
    }

    @Override
    public void deleteProductById(Integer id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Integer getLastInsertedProductId() {
        Iterable<Product> products = this.getAllProducts();
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(product);
        }
        int index = productList.size() - 1;
        Product lastInsertedProduct = productList.get(index);

        return lastInsertedProduct.getId();
    }

    @Override
    public void updateDiscountPrice(List<Product> productList) {
        for (Product product : productList) {
            product.setDiscountPrice(0.00);
            List<Discount> discountList = product.getDiscounts();
            if (discountList != null && !discountList.isEmpty()) {
                Discount bestDiscount = this.discountService.getCurrentActivatedBestDiscount(discountList);
                //B: to put in ProductServiceImpl:
                Double rawDiscountPrice = product.getPrice() - (product.getPrice() * bestDiscount.getRate()) / 100;
                Double discountPrice = (Math.ceil(rawDiscountPrice * 100)) / 100;
                //B: end
                product.setDiscountPrice(discountPrice);
                this.saveProduct(product);
            }
        }
    }
    
}
