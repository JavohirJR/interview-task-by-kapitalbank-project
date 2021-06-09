package com.javohir.lastproject.repository;

import com.javohir.lastproject.entity.Category;
import com.javohir.lastproject.entity.Detail;
import com.javohir.lastproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
