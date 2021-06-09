package com.javohir.lastproject.controller;

import com.javohir.lastproject.entity.Payment;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.PaymentDTO;
import com.javohir.lastproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping()
    public HttpEntity<?> getById(@RequestParam Integer payment_id){
        ApiResponse response = paymentService.getPayment(payment_id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<Payment> response = paymentService.getAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody PaymentDTO paymentDTO){
        ApiResponse response = paymentService.addPayment(paymentDTO);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

}
