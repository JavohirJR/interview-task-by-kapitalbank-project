package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.Category;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.repository.CategoryRepository;
import com.javohir.lastproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getByProductId(Integer id) {
        return categoryRepository.findByProductId(id);
    }

    public ApiResponse addCategory(String name) {
        boolean existsByName = categoryRepository.existsByName(name);
        if (existsByName) return new ApiResponse("Category is already exist", false);

        categoryRepository.save(new Category(name));
        return new ApiResponse("Category added successfully", true);
    }

    public ApiResponse editCategory(String name, Integer id) {
        boolean existsByName = categoryRepository.existsByName(name);
        if (existsByName) return new ApiResponse("Category is already exist", false);

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new ApiResponse("Category is not found", false);

        Category category = optionalCategory.get();
        category.setName(name);

        categoryRepository.save(category);
        return new ApiResponse("Category edited successfully", true);
    }

    public ApiResponse deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new ApiResponse("Category is not found", false);
        categoryRepository.deleteById(id);
        return new ApiResponse("Category deleted successfully", true);
    }

}
