package com.javohir.lastproject.controller;

import com.javohir.lastproject.entity.Customer;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.CustomerDTO;
import com.javohir.lastproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public HttpEntity<?> getById(@RequestParam Integer product_id){
        ApiResponse response = customerService.getCustomer(product_id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<Customer> response = customerService.getAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CustomerDTO customerDTO){
        ApiResponse response = customerService.addCustomer(customerDTO);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id){
        ApiResponse response = customerService.editCustomer(customerDTO, id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = customerService.deleteCustomer(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

}
