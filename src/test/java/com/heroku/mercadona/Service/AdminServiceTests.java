package com.heroku.mercadona.Service;

import com.heroku.mercadona.model.Admin;
import com.heroku.mercadona.repository.AdminRepository;
import com.heroku.mercadona.service.impl.AdminServiceImpl;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTests {

    @Mock
    private AdminRepository adminRepositroy;

    @InjectMocks
    private AdminServiceImpl adminService;

    private Admin admin;

    @BeforeEach
    public void setup() {
        admin = new Admin();
        admin.setId(1);
        admin.setName("Admin");
    }

    @DisplayName("JUnit test for saveAdmin method")
    @Test
    public void givenAdminObject_whenSaveAdmin_thenReturnAdminObject() {
        //given
        given(adminRepositroy.findById(admin.getId()))
                .willReturn(Optional.empty());

        given(adminRepositroy.save(admin)).willReturn(admin);

        //when        
        Admin savedAdmin = adminService.saveAdmin(admin);

        //then
        assertThat(savedAdmin).isNotNull();
    }

    @DisplayName("JUnit test for saveAdmin method which throws exception")
    @Test
    public void givenAdminObject_whenSaveAdmin_thenThrowsException() {
        //given
        given(adminRepositroy.findById(admin.getId()))
                .willReturn(Optional.of(admin));

        //when        
        org.junit.jupiter.api.Assertions.assertThrows(InvalidConfigurationPropertyValueException.class, () -> {
            adminService.saveAdmin(admin);
        }, "InvalidConfigurationPropertyValueException was expected");

        //then
        verify(adminRepositroy, never()).save(ArgumentMatchers.any(Admin.class));
    }

    @DisplayName("JUnit test for getAdminByName method")
    @Test
    public void givenAdminName_whenGetAdminByName_thenReturnAdminObject() {
        // given
        given(adminRepositroy.findByName("Admin")).willReturn(admin);

        // when
        Admin savedAdmin = adminService.getAdminByName(admin.getName());

        //then
        assertThat(savedAdmin).isNotNull();
    }
}
