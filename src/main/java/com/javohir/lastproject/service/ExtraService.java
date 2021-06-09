package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.Customer;
import com.javohir.lastproject.entity.Invoice;
import com.javohir.lastproject.entity.Order;
import com.javohir.lastproject.repository.CustomerRepository;
import com.javohir.lastproject.repository.DetailRepository;
import com.javohir.lastproject.repository.InvoiceRepository;
import com.javohir.lastproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DetailRepository detailRepository;

    public List<Invoice> getExpiredInvoices() {
        return invoiceRepository.getExpiredInvoices();
    }

    public List<Customer> getCustomerLastOrder(String year) {
        return customerRepository.getCustomersWithoutOrders(year);
    }

    public List<Order> getOrdersWithoutDetails(String year) {
        return orderRepository.getOrdersWithoutDetails(year);
    }

    public List<?> getHighDemandProducts() {
        return detailRepository.getHighDemandProducts();
    }

    public List<?> getBulkProducts() {
        return detailRepository.getBulkProducts();
    }

    public List<?> getNumberOfProductsInYear(Integer year) {
        return customerRepository.getNumberOfProductsInYear(year);
    }

    public List<?> getCustomerLastOrder() {
        return orderRepository.getCustomerLastOrder();
    }

    public List<?> getOverpaidInvoices() {
        return invoiceRepository.getOverpaidInvoices();
    }

}
