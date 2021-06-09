package com.javohir.lastproject.repository;

import com.javohir.lastproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select c.id, c.name from product as p join category c on p.category_id = c.id where p.id=:productId", nativeQuery = true)
    Category findByProductId(Integer productId);

    boolean existsByName(String name);

}
