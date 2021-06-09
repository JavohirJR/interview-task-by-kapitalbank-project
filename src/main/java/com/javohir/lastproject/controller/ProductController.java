package com.javohir.lastproject.controller;

import com.javohir.lastproject.entity.Product;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.OrderDTO;
import com.javohir.lastproject.payload.OrderEditDTO;
import com.javohir.lastproject.payload.ProductDTO;
import com.javohir.lastproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public HttpEntity<?> getById(@RequestParam Integer product_id){
        ApiResponse response = productService.getProduct(product_id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<Product> response = productService.getAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody ProductDTO productDTO){
        ApiResponse response = productService.addWithProduct(productDTO);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PostMapping("/add-photo")
    public HttpEntity<?> addPhoto(@RequestBody ProductDTO productDTO, MultipartHttpServletRequest request){
        ApiResponse response = productService.addWithPhotoProduct(productDTO, request);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody ProductDTO productDTO, @PathVariable Integer id){
        ApiResponse response = productService.editProduct(productDTO, id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = productService.deleteProduct(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

}
