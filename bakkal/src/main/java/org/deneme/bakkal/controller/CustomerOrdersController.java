package org.deneme.bakkal.controller;

import io.micrometer.common.KeyValues;
import org.deneme.bakkal.CalculateCustomerDebthRequest;
import org.deneme.bakkal.SellProductRequest;
import org.deneme.bakkal.model.Customer;
import org.deneme.bakkal.model.Product;
import org.deneme.bakkal.repository.CustomerRepository;
import org.deneme.bakkal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.deneme.bakkal.model.CustomerOrders;
import org.deneme.bakkal.exception.ResourceNotFoundException;
import org.deneme.bakkal.repository.CustomerOrdersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<String, Double> getAllCustomerOrders() {
        List<CustomerOrders> orders = customerOrdersRepository.findAll();
        Map<String, Double> orderSummary = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getCustomer().getFirstName(),
                        Collectors.summingDouble(order -> order.getProduct().getProductPrice() * order.getQuantity())
                ));
        return orderSummary;
    }

    @GetMapping("/calculate-customer-debth/{id}")
    public Double customerDebth(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + "numaralı kişi bulunamadı"));
        List <CustomerOrders> orders = customerOrdersRepository.findByCustomer_Id(id);

        double totalDebth = orders.stream().collect(Collectors.summingDouble(order -> order.getProduct().getProductPrice() * order.getQuantity()));
        return totalDebth;
    }


}
