package org.deneme.bakkal.controller;

import org.deneme.bakkal.SellProductRequest;
import org.deneme.bakkal.repository.CustomerRepository;
import org.deneme.bakkal.repository.ProductRepository;
import org.deneme.bakkal.service.CustomerOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.deneme.bakkal.model.CustomerOrders;
import org.deneme.bakkal.repository.CustomerOrdersRepository;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shop-api/")
public class CustomerOrdersController {
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;
    @Autowired
    private CustomerOrdersService customerOrdersService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/sell-product2")
    public CustomerOrders customerOrders(@RequestBody SellProductRequest input) {
        return customerOrdersService.sellProduct(input);
    }

    @GetMapping("/calculate-customer-debth")
    public Map<String, Double> getAllCustomerOrders() {
        return customerOrdersService.getAllCustomerOrders();
    }

    @GetMapping("/calculate-customer-debth/{id}")
    public Double customerDebth(@PathVariable Long id) {
        return customerOrdersService.calculateCustomerDebth(id);
    }

    @GetMapping("/calculate-customer-debth-jpql/{id}")
    public Double customerDebth2(@PathVariable Long id) {
        return customerOrdersService.calculateCustomerDebth2(id);
    }

    @GetMapping("/product-buyers/{id}")
    public List<String> getProductBuyers(@PathVariable Long id) {
        return customerOrdersService.getProductBuyers(id);

    }

}
