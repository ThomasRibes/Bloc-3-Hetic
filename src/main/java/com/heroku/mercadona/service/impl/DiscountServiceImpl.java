package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.repository.DiscountRepository;
import com.heroku.mercadona.service.DiscountService;
import java.util.List;
import java.util.Optional;
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

    @Override
    public void deleteDiscountById(Integer id) {
        this.discountRepository.deleteById(id);
    }

    @Override
    public Discount getDiscountById(Integer id) {
        Optional<Discount> optional = discountRepository.findById(id);
        Discount discount = null;
        if (optional.isPresent()) {
            discount = optional.get();
        } else {
            throw new RuntimeException("Discount not found for id : " + id);
        }
        return discount;
    }
    
    @Override
    public List<Discount> getAllDiscounts(){
         return (List<Discount>) discountRepository.findAll();
    }

}
