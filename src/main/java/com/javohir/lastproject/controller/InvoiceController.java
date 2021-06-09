package com.javohir.lastproject.controller;

import com.javohir.lastproject.entity.Invoice;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    public HttpEntity<?> getByProductId(@RequestParam Integer product_id){
        ApiResponse response = invoiceService.getInvoice(product_id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<Invoice> response = invoiceService.getAll();
        return ResponseEntity.ok(response);
    }
}
