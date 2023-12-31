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
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, DiscountService discountService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.discountService = discountService;
    }

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final DiscountService discountService;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public boolean checkIfParamMatchACategory(Integer categoryId) {
        Boolean paramMatchCategory = false;
        List<Category> listOfCategories = categoryService.getAllCategories();
        for (Category category : listOfCategories) {
            if (Objects.equals(category.getId(), categoryId)) {
                paramMatchCategory = true;
            }
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
    public boolean checkIfParamMatchZero(Integer categoryId) {
        Boolean paramMatchNull = false;
        if (categoryId == 0) {
            paramMatchNull = true;
        }
        return paramMatchNull;
    }

    @Override
    public List<Product> getActiveProductListByCategory(Integer categoryId) {
        List<Product> productListByCategory = manager.createNamedQuery("getProductListByCategory", Product.class)
                .setParameter(1, categoryId)
                .getResultList();
        return productListByCategory;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
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
            product.setDiscountPrice(product.getPrice());
            this.saveProduct(product);
            List<Discount> discountList = product.getDiscounts();
            if (discountList != null && !discountList.isEmpty()) {
                Discount bestDiscount = this.discountService.getCurrentActivatedBestDiscount(discountList);
                Double discountPrice = calculateDiscountPrice(product.getPrice(), bestDiscount);
                product.setDiscountPrice(discountPrice);
                this.saveProduct(product);
            }
        }
    }

    private Double calculateDiscountPrice(Double price, Discount discount) {
        Double rawDiscountPrice = price - (price * discount.getRate()) / 100;
        return (Math.ceil(rawDiscountPrice * 100)) / 100;
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findAllActiveProducts();
    }
    @Override
    public Product getLastEntryProduct() {
        return productRepository.findTopByOrderByIdDesc();
    }

}
