package com.javohir.lastproject.controller;

import com.javohir.lastproject.entity.Category;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public HttpEntity<?> getByProductId(@RequestParam Integer product_id){
        Category response = categoryService.getByProductId(product_id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<Category> response = categoryService.getAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public HttpEntity<?> addCategory(@RequestParam String name){
        ApiResponse response = categoryService.addCategory(name);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editCategory(@RequestBody String name, @PathVariable Integer id){
        ApiResponse response = categoryService.editCategory(name, id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Integer id){
        ApiResponse response = categoryService.deleteCategory(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

}
