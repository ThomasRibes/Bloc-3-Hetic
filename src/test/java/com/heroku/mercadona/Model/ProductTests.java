
package com.heroku.mercadona.Model;

import com.heroku.mercadona.model.Admin;
import com.heroku.mercadona.model.Category;
import com.heroku.mercadona.model.Discount;
import com.heroku.mercadona.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTests {

    @Test
    public void unitTestProductSetIdAndGetId() {
        //Arrange
        Product product = new Product();
        Integer expected = 0;
        //Act
        product.setId(0);
        Integer actual = product.getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductSetLabelAndGetLabel() {
        //Arrange
        Product product = new Product();
        String expected = "label";
        //Act
        product.setLabel("label");
        String actual = product.getLabel();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductSetDescriptionAndGetDescription() {
        //Arrange
        Product product = new Product();
        String expected = "description";
        //Act
        product.setDescription("description");
        String actual = product.getDescription();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductSetPriceAndGetPrice() {
        //Arrange
        Product product = new Product();
        Double expected = 10.00;
        //Act
        product.setPrice(10.00);
        Double actual = product.getPrice();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductSetUrlAndGetUrl() {
        //Arrange
        String expected = "url";
        Product product = new Product();
        //Act
        product.setUrl("url");
        String actual = product.getUrl();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductSetIs_activeAndGetIs_active() {
        //Arrange
        Product product = new Product();
        //Act
        product.set_active(false);
        Boolean actual = product.is_active();
        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    public void unitTestProductSetCategoryAndGetCategory() {
        //Arrange
        Category category = new Category();
        category.setId(555);
        Product product = new Product();
        Integer expected = 555;
        //Act
        product.setCategory(category);
        Integer actual = product.getCategory().getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductSetAdminAndGetAdmin() {
        //Arrange
        Admin admin = new Admin();
        admin.setId(111);
        Product product = new Product();
        Integer expected = 111;
        //Act
        product.setAdmin(admin);
        Integer actual = product.getAdmin().getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductSetDiscountAndGetDiscount() {
        //Arrange
        Discount discount = new Discount();
        discount.setId(222);
        List<Discount> discountList = new ArrayList<>();
        discountList.add(discount);
        Product product = new Product();
        Integer expected = 222;
        //Act
        product.setDiscounts(discountList);
        Integer actual = product.getDiscounts().get(0).getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestProductToString() {
        //Arrange
        Product product = new Product();
        product.setId(1);
        product.setLabel("tested product");
        product.setDescription("product test");
        product.setPrice(50.0);
        product.setUrl("product test url");
        product.set_active(true);
        String expected = "Product{id=1, label=tested product, description=product test, price=50.0, url=product test url, is_active=true}";
        //Act
        String actual = product.toString();
        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
