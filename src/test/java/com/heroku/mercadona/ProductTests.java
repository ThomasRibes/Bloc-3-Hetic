
package com.heroku.mercadona;

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
    public void unitTestSetIdAndGetId() {
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
    public void unitTestSetLabelAndGetLabel() {
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
    public void unitTestSetDescriptionAndGetDescription() {
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
    public void unitTestSetPriceAndGetPrice() {
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
    public void unitTestSetUrlAndGetUrl() {
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
    public void unitTestSetIs_activeAndGetIs_active() {
        //Arrange
        Product product = new Product();
        //Act
        product.setIs_active(false);
        Boolean actual = product.getIs_active();
        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    public void unitTestSetCategoryAndGetCategory() {
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
    public void unitTestSetAdminAndGetAdmin() {
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
    public void unitTestSetDiscountAndGetDiscount() {
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
    public void unitTestToString() {
        //Arrange
        Product product = new Product();
        product.setId(1);
        product.setLabel("tested product");
        product.setDescription("product test");
        product.setPrice(50.0);
        product.setUrl("product test url");
        product.setIs_active(true);
        String expected = "Product{id=1, label=tested product, description=product test, price=50.0, url=product test url, is_active=true}";
        //Act
        String actual = product.toString();
        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
