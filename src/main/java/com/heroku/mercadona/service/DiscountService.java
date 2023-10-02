package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Discount;
import java.util.List;

public interface DiscountService {
    void saveDiscount(Discount discount);
    void deleteDiscountById(Integer id);
    Discount getDiscountById(Integer id);
    List<Discount> getAllDiscounts();
    Discount getCurrentActivatedBestDiscount(List<Discount> discountList);
}