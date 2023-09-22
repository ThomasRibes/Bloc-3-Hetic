package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Discount;

public interface DiscountService {

    void saveDiscount(Discount discount);
    void deleteDiscountById(Integer id);
}
