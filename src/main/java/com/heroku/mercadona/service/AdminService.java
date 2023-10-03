
package com.heroku.mercadona.service;

import com.heroku.mercadona.model.Admin;

public interface AdminService {
    Admin getAdminByName(String name);
    String getAuthenticatedAdminName();
    void saveAdmin(Admin admin);
}