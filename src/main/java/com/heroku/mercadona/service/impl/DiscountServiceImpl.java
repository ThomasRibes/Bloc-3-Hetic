package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.repository.DiscountRepository;
import com.heroku.mercadona.service.DiscountService;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public void saveDiscount(Discount discount) {
        this.discountRepository.save(discount);
    }

}