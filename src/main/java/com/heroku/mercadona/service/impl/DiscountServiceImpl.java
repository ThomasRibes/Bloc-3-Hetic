package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.DiscountRepository;
import com.heroku.mercadona.repository.ProductRepository;
import com.heroku.mercadona.service.DiscountService;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void saveDiscount(Discount discount, Product product) {
        this.productRepository.save(product);
        this.discountRepository.save(discount);
    }

}