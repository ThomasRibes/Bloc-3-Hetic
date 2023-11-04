package com.heroku.mercadona.Service;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.repository.DiscountRepository;
import com.heroku.mercadona.service.impl.DiscountServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscountServiceTests {

    @Mock
    private DiscountRepository discountRepository;

    @InjectMocks
    private DiscountServiceImpl discountService;

    private Discount discount;

    @BeforeEach
    public void setup() {
        discount = new Discount();
        discount.setId(1);
        discount.setRate(50);
        discount.setIs_active(true);
    }

    @DisplayName("JUnit test for saveDiscount method")
    @Test
    public void givenDiscountObject_whenSaveDiscount_thenReturnDiscountObject() {
        //given
        given(discountRepository.findById(discount.getId()))
                .willReturn(Optional.empty());

        given(discountRepository.save(discount)).willReturn(discount);

        //when        
        Discount savedDiscount = discountService.saveDiscount(discount);

        //then
        assertThat(savedDiscount).isNotNull();
    }

    @DisplayName("JUnit test for getAllDiscounts method")
    @Test
    public void givenDiscountList_whenGetAllDiscounts_thenReturnDiscountsList() {
        // given

        Discount discount2 = new Discount();
        discount.setId(2);
        discount.setRate(20);
        discount.setIs_active(false);

        given(discountRepository.findAll()).willReturn(List.of(discount, discount2));

        // when
        List<Discount> discountList = discountService.getAllDiscounts();

        // then
        assertThat(discountList).isNotNull();
        assertThat(discountList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getDiscountById method")
    @Test
    public void givenDiscountId_whenGetDiscountById_thenReturnDiscountObject() {
        // given
        given(discountRepository.findById(1)).willReturn(Optional.of(discount));

        // when
        Discount savedDiscount = discountService.getDiscountById(discount.getId());

        //then
        assertThat(savedDiscount).isNotNull();
    }

    @DisplayName("JUnit test for deleteDiscountById method")
    @Test
    public void givenDiscountId_whenDeleteDiscountById_thenNothing() {
        // given
        Integer discountId = 1;

        willDoNothing().given(discountRepository).deleteById(discountId);

        // when
        discountService.deleteDiscountById(discountId);

        //then
        verify(discountRepository, times(1)).deleteById(discountId);
    }

}
