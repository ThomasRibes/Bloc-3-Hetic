
package com.heroku.mercadona.repository;

import com.heroku.mercadona.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
