package com.heroku.mercadona.UnitTests;

import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscountTests {

    @Test
    public void unitTestDiscountSetIdAndGetId() {
        //Arrange
        Discount discount = new Discount();
        Integer expected = 0;
        //Act
        discount.setId(0);
        Integer actual = discount.getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestDiscountSetRateAndGetRate() {
        //Arrange
        Discount discount = new Discount();
        Integer expected = 30;
        //Act
        discount.setRate(30);
        Integer actual = discount.getRate();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestDiscountSetStartDateAndGetStartDate() {
        //Arrange
        SimpleDateFormat formater = null;
        Discount discount = new Discount();
        Date date = new Date();
        formater = new SimpleDateFormat("dd/MM/yyyy");
        String expected = formater.format(date);
        //Act
        discount.setStartDate(new Date());
        String actual = formater.format(discount.getStartDate());
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestDiscountSetEndDateAndGetEndDate() {
        //Arrange
        SimpleDateFormat formater = null;
        Discount discount = new Discount();
        Date date = new Date();
        formater = new SimpleDateFormat("dd/MM/yyyy");
        String expected = formater.format(date);
        //Act
        discount.setEndDate(new Date());
        String actual = formater.format(discount.getEndDate());
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestDiscountSetIs_activeAndGetIs_active() {
        //Arrange
        Discount discount = new Discount();
        //Act
        discount.setIs_active(false);
        Boolean actual = discount.getIs_active();
        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    public void unitTestDiscountSetProductsAndGetProducts() {
        //Arrange
        Product product = new Product();
        product.setId(777);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Discount discount = new Discount();
        Integer expected = 777;
        //Act
        discount.setProducts(productList);
        Integer actual = discount.getProducts().get(0).getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestDiscountToString() {
        //Arrange
        Discount discount = new Discount();
        discount.setId(1);
        discount.setRate(50);
        discount.setStartDate(new Date(0));
        discount.setEndDate(new Date(0));
        discount.setIs_active(true);
        String expected = "Discount{id=1, rate=50, startDate=Thu Jan 01 01:00:00 CET 1970, endDate=Thu Jan 01 01:00:00 CET 1970, is_active=true}";
        //Act
        String actual = discount.toString();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

}
