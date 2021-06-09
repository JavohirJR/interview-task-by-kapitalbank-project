package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.Invoice;
import com.javohir.lastproject.entity.Payment;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.PaymentDTO;
import com.javohir.lastproject.repository.InvoiceRepository;
import com.javohir.lastproject.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    InvoiceRepository invoiceRepository;


    public ApiResponse addPayment(PaymentDTO paymentDTO) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentDTO.getInvoiceId());
        if (!optionalInvoice.isPresent()) return new ApiResponse("FAILED, Invoice is not found", false);

        Invoice invoice = optionalInvoice.get();
        if (invoice.isPaid())
            return new ApiResponse("This invoice has been already paid", false);
        if (invoice.getAmount() < paymentDTO.getAmount() || 0 > paymentDTO.getAmount())
            return new ApiResponse("Please, enter valid amount, your amount is more than invoice amount or less than 0", false);

        if (invoice.getAmount() - paymentDTO.getAmount() == 0) {
            invoice.setPaid(true);
        }
        invoice.setAmount(invoice.getAmount()- paymentDTO.getAmount());

        paymentRepository.save(new Payment(
                new Timestamp(System.currentTimeMillis()),
                paymentDTO.getAmount(),
                invoice
        ));
        invoiceRepository.save(invoice);
        return new ApiResponse("SUCCESS, Payment is added successfully", true);
    }

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    public ApiResponse getPayment(Integer id){
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.map(payment -> new ApiResponse("SUCCESS", true, payment)).orElseGet(() -> new ApiResponse("Payment is not found", false));
    }

}
