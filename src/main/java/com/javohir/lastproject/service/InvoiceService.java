package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.Invoice;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse getInvoice(Integer id){
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        return optionalInvoice.map(invoice -> new ApiResponse("Success", true, invoice)).orElseGet(() -> new ApiResponse("Invoice is not found", false));
    }
    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }
}
