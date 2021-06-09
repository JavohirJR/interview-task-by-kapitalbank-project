package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.Customer;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.CustomerDTO;
import com.javohir.lastproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public ApiResponse getCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customer -> new ApiResponse("Success", true, customer)).orElseGet(() -> new ApiResponse("Customer is not found", false));
    }

    public ApiResponse addCustomer(CustomerDTO customerDTO) {
        boolean existsByPhoneNumber = customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber());
        if (existsByPhoneNumber) return new ApiResponse("Customer is already exist", false);
        customerRepository.save(new Customer(
                customerDTO.getName(),
                customerDTO.getCountry(),
                customerDTO.getPhoneNumber(),
                customerDTO.getText()
        ));
        return new ApiResponse("Customer is added successfully", true);
    }

    public ApiResponse editCustomer(CustomerDTO customerDTO, Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) return new ApiResponse("Customer is not found", false);
        Customer customer = optionalCustomer.get();
        if (!customer.getPhoneNumber().equals(customerDTO.getPhoneNumber())){
            boolean existsByPhoneNumber = customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber());
            if (existsByPhoneNumber) return new ApiResponse("Please, enter another phone number. This phone number is already exist", false);
            customer.setPhoneNumber(customerDTO.getPhoneNumber());
        }
        customer.setCountry(customerDTO.getCountry());
        customer.setName(customerDTO.getName());
        customer.setText(customerDTO.getText());
        customerRepository.save(customer);
        return new ApiResponse("Customer is edited successfully",true);
    }

    public ApiResponse deleteCustomer(Integer id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            customerRepository.delete(optionalCustomer.get());
            return new ApiResponse("Customer is deleted successfully", true);
        }else return new ApiResponse("Customer is not found", false);
    }

}
