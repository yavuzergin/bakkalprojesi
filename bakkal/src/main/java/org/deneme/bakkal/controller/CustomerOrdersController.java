package org.deneme.bakkal.controller;

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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shop-api/")
public class CustomerOrdersController {
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @PostMapping("/sell-product")
    public CustomerOrders createCustomerOrders (@RequestBody CustomerOrders customerOrders){
        return customerOrdersRepository.save(customerOrders);
    }


}
