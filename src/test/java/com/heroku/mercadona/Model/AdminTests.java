package com.heroku.mercadona.Model;

import com.heroku.mercadona.model.Admin;
import com.heroku.mercadona.model.AdminState;
import com.heroku.mercadona.model.Product;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTests {

    @Test
    public void unitTestAdminSetIdAndGetId() {
        //Arrange
        Admin admin = new Admin();
        Integer expected = 0;
        //Act
        admin.setId(0);
        Integer actual = admin.getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminSetNameAndGetName() {
        //Arrange
        Admin admin = new Admin();
        String expected = "name";
        //Act
        admin.setName("name");
        String actual = admin.getName();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminSetEmailAndGetEmail() {
        //Arrange
        Admin admin = new Admin();
        String expected = "email";
        //Act
        admin.setEmail("email");
        String actual = admin.getEmail();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminSetPasswordAndGetPassword() {
        //Arrange
        Admin admin = new Admin();
        String expected = "password";
        //Act
        admin.setPassword("password");
        String actual = admin.getPassword();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminSetStateAndGetState() {
        //Arrange
        Admin admin = new Admin();
        String expected = "REDEFINE_PW_REQUIRED";
        //Act
        admin.setState(AdminState.REDEFINE_PW_REQUIRED);
        String actual = admin.getState().toString();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminSetCreated_atAndGetCreated_at() {
        //Arrange
        Admin admin = new Admin();
        LocalDate expected = LocalDate.now();
        //Act
        admin.setCreated_at(expected);
        LocalDate actual = admin.getCreated_at();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminSetIs_activeAndGetIs_active() {
        //Arrange
        Admin admin = new Admin();
        //Act
        admin.setIs_active(false);
        Boolean actual = admin.getIs_active();
        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    public void unitTestAdminSetProductAndGetProduct() {
        //Arrange
        Product product = new Product();
        product.setId(888);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Admin admin = new Admin();
        Integer expected = 888;
        //Act
        admin.setProducts(productList);
        Integer actual = admin.getProducts().get(0).getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminSetRoleAndGetRole() {
        //Arrange
        Admin admin = new Admin();
        String expected = "ADMIN";
        //Act
        admin.setRole("ADMIN");
        String actual = admin.getRole();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminAddProduct() {
        //Arrange
        Product product = new Product();
        product.setId(5);
        Admin admin = new Admin();
        Integer expected = 5;
        //Act
        admin.addProduct(product);
        Integer actual = admin.getProducts().get(0).getId();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestAdminRemoveProduct() {
        //Arrange
        Product product = new Product();
        product.setId(5);
        Admin admin = new Admin();
        admin.addProduct(product);
        //Act
        admin.removeProduct(product);
        //Assert
        Assertions.assertFalse(admin.getProducts().contains(product));
    }

    @Test
    public void unitTestAdminToString() {
        //Arrange
        Admin admin = new Admin();
        admin.setId(1);
        admin.setName("admin");
        admin.setEmail("admin@admin.fr");
        admin.setPassword("password");
        admin.setState(AdminState.PW_REDEFINED);
        admin.setCreated_at(LocalDate.of(2023, Month.JANUARY, 1));
        admin.setIs_active(false);
        admin.setRole("ADMIN");
        String expected = "Admin{id=1, name=admin, email=admin@admin.fr, password=password, state=PW_REDEFINED, created_at=2023-01-01, is_active=false, role=ADMIN}";
        //Act
        String actual = admin.toString();
        //Assert
        Assertions.assertEquals(expected, actual);
    }

}
