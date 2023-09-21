package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;


public interface DiscountService {
    void saveDiscount(Discount discount, Product product);
}