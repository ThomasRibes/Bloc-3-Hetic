package com.heroku.mercadona.service.impl;

import com.heroku.mercadona.model.Admin;
import com.heroku.mercadona.repository.AdminRepository;
import com.heroku.mercadona.service.AdminService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin getAdminByName(String name) {
        return adminRepository.findByName(name);
    }

    @Override
    public String getAuthenticatedAdminName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        Optional<Admin> savedAdmin = adminRepository.findById(admin.getId());
        if (!savedAdmin.isPresent()) {
            throw new InvalidConfigurationPropertyValueException("admin", admin, "Admin does not exist with given id:" + admin.getId());
        }
        return adminRepository.save(admin);
    }

    
}
