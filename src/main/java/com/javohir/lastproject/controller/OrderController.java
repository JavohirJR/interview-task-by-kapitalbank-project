package com.javohir.lastproject.controller;

import com.javohir.lastproject.entity.Order;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.OrderDTO;
import com.javohir.lastproject.payload.OrderEditDTO;
import com.javohir.lastproject.payload.OrderOutDTO;
import com.javohir.lastproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public HttpEntity<?> getById(@RequestParam Integer product_id){
        ApiResponse response = orderService.getOrder(product_id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<OrderOutDTO> response = orderService.getAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody OrderDTO orderDTO){
        ApiResponse response = orderService.addOrder(orderDTO);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody OrderEditDTO editDTO, @PathVariable Integer id){
        ApiResponse response = orderService.editOrder(editDTO, id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = orderService.deleteOrder(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

}
