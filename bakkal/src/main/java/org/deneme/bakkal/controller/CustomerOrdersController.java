package org.deneme.bakkal.controller;

import org.deneme.bakkal.CalculateCustomerDebthRequest;
import org.deneme.bakkal.SellProductRequest;
import org.deneme.bakkal.model.Customer;
import org.deneme.bakkal.model.Product;
import org.deneme.bakkal.repository.CustomerRepository;
import org.deneme.bakkal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.deneme.bakkal.model.CustomerOrders;
import org.deneme.bakkal.exception.ResourceNotFoundException;
import org.deneme.bakkal.repository.CustomerOrdersRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shop-api/")
public class CustomerOrdersController {
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/sell-product2")
    public CustomerOrders customerOrders(@RequestBody SellProductRequest input) {
        final Customer customer = customerRepository.findById(input.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException(input.getCustomerId() + " numaralı kişi mevcut değildir."));
        final Product product = productRepository.findById(input.getProductId()).orElseThrow(() -> new ResourceNotFoundException(input.getProductId() + " numaralı ürün mevcut değildir."));
        final CustomerOrders customerOrder = new CustomerOrders();
        customerOrder.setCustomer(customer);
        customerOrder.setProduct(product);
        customerOrder.setQuantity(input.getQuantity());
        double totalCost = input.getQuantity() * product.getProductPrice();
        customerOrder.setTotal_cost(totalCost);

        return customerOrdersRepository.save(customerOrder);
    }
    @GetMapping("/calculate-customer-debth")
    public List<CustomerOrders> getAllCustomerOrders() {
        return customerOrdersRepository.findAll();

    }
}
