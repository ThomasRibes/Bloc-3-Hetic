package com.heroku.mercadona.Service;

import com.heroku.mercadona.model.Product;
import com.heroku.mercadona.repository.ProductRepository;
import com.heroku.mercadona.service.impl.ProductServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepositroy;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setId(1);
        product.setDescription("productTest");
        product.setLabel("productTest");
        product.setIs_active(true);
        product.setPrice(10.0);
        product.setUrl("productUrl");
    }

    @DisplayName("JUnit test for saveProduct method")
    @Test
    public void givenProductObject_whenSaveProduct_thenReturnProductObject() {
        //given
        given(productRepositroy.findById(product.getId()))
                .willReturn(Optional.empty());

        given(productRepositroy.save(product)).willReturn(product);

        //when        
        Product savedProduct = productService.saveProduct(product);

        //then
        assertThat(savedProduct).isNotNull();
    }

    @DisplayName("JUnit test for getAllProducts method")
    @Test
    public void givenProductList_whenGetAllProducts_thenReturnProductsList() {
        //given
        Product product2 = new Product();
        product.setId(2);
        product.setDescription("product2Test2");
        product.setLabel("product2Test");
        product.setIs_active(true);
        product.setPrice(2.0);
        product.setUrl("product2Url");

        given(productRepositroy.findAll()).willReturn(List.of(product2, product2));

        //when        
        List<Product> productList = productService.getAllProducts();

        //then
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getProductById method")
    @Test
    public void givenProductId_whenGetProductById_thenReturnProductObject() {
        // given
        given(productRepositroy.findById(1)).willReturn(Optional.of(product));

        // when
        Product savedProduct = productService.getProductById(product.getId());

        //then
        assertThat(savedProduct).isNotNull();
    }

    @DisplayName("JUnit test for deleteProductById method")
    @Test
    public void givenProductId_whenDeleteProductById_thenNothing() {
        // given
        Integer productId = 1;

        willDoNothing().given(productRepositroy).deleteById(productId);

        // when
        productService.deleteProductById(productId);

        //then
        verify(productRepositroy, times(1)).deleteById(productId);
    }
}
