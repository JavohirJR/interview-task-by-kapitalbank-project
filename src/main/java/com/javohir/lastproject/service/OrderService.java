package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.*;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.OrderDTO;
import com.javohir.lastproject.payload.OrderEditDTO;
import com.javohir.lastproject.payload.OrderOutDTO;
import com.javohir.lastproject.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DetailRepository detailRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse addOrder(OrderDTO orderDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerId());
        if (!optionalCustomer.isPresent()) return new ApiResponse("Customer is not found, FAILED", false);

        Optional<Product> optionalProduct = productRepository.findById(orderDTO.getProductId());
        if (!optionalProduct.isPresent()) return new ApiResponse("Product is not found, FAILED", false);

        Order savedOrder = orderRepository.save(new Order(
                new Date(System.currentTimeMillis()),
                optionalCustomer.get()
        ));

        detailRepository.save(new Detail(
                orderDTO.getQuantity(),
                savedOrder,
                optionalProduct.get()
        ));

        Invoice invoice = new Invoice();
        invoice.setOrder(savedOrder);
        double price = optionalProduct.get().getPrice();
        invoice.setAmount(orderDTO.getQuantity()*price);
        invoice.setIssued(new Date(System.currentTimeMillis()));
        Date date = savedOrder.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 3);
        invoice.setDue(new Date(calendar.getTimeInMillis()));
        Invoice savedInvoice = invoiceRepository.save(invoice);

        return new ApiResponse("SUCCESS, Order is added successfully", true, "Invoice ID : "+savedInvoice.getId());
    }

    public List<OrderOutDTO> getAll(){
        List<OrderOutDTO> orderOutDTOS = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            Optional<Order> optionalOrder = orderRepository.findById(order.getId());
            if (!optionalOrder.isPresent()) return null;
            List<Detail> detailList = detailRepository.findByOrderId(order.getId());
            orderOutDTOS.add(new OrderOutDTO(optionalOrder.get(), detailList));
        }

        return orderOutDTOS;
    }

    public ApiResponse getOrder(Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Order is not found", false);
        List<Detail> detailList = detailRepository.findByOrderId(id);

        return new ApiResponse("SUCCESS", true, new OrderOutDTO(optionalOrder.get(), detailList));
    }

    public ApiResponse editOrder(OrderEditDTO orderDTO, Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Order is not found, FAILED", false);

        Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerId());
        if (!optionalCustomer.isPresent()) return new ApiResponse("Customer is not found, FAILED", false);

        Optional<Product> optionalProduct = productRepository.findById(orderDTO.getProductId());
        if (!optionalProduct.isPresent()) return new ApiResponse("Product is not found, FAILED", false);

        Optional<Detail> optionalDetail = detailRepository.findById(orderDTO.getDetailId());
        if (!optionalDetail.isPresent()) return new ApiResponse("Detail is not found, FAILED", false);

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(orderDTO.getInvoiceId());
        if (!optionalInvoice.isPresent()) return new ApiResponse("Invoice is not found, FAILED", false);

        Order editedOrder = optionalOrder.get();
        editedOrder.setCustomer(editedOrder.getCustomer());
        editedOrder.setDate(new Date(System.currentTimeMillis()));
        Order savedOrder = orderRepository.save(editedOrder);

        Detail editedDetail = optionalDetail.get();
        editedDetail.setOrder(savedOrder);
        editedDetail.setProduct(optionalProduct.get());
        editedDetail.setQuantity(orderDTO.getQuantity());
        Detail savedDetail = detailRepository.save(editedDetail);


        Invoice editedInvoice = optionalInvoice.get();
        editedInvoice.setOrder(savedOrder);
        double price = optionalProduct.get().getPrice();
        editedInvoice.setAmount(orderDTO.getQuantity()*price);
        editedInvoice.setIssued(new Date(System.currentTimeMillis()));
        Date date = savedOrder.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 3);
        editedInvoice.setDue(new Date(calendar.getTimeInMillis()));
        Invoice savedInvoice = invoiceRepository.save(editedInvoice);
        return new ApiResponse("Order is edited successfully, SUCCESS", true, savedInvoice.getId());

    }

    public ApiResponse deleteOrder(Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Order is not found, FAILED", false);
        orderRepository.delete(optionalOrder.get());
        return new ApiResponse("Order deleted successfully, SUCCESS", true);
    }

}
