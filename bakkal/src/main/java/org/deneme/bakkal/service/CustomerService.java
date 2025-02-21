package org.deneme.bakkal.service;

import org.deneme.bakkal.model.Customer;
import org.deneme.bakkal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.deneme.bakkal.exception.ResourceNotFoundException;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı kişi bulunamadı."));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı kişi bulunamadı"));
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.setEmailId(customerDetails.getEmailId());
        customer.setCustomerAddress(customerDetails.getCustomerAddress());
        return customerRepository.save(customer);
    }

    public Map<String, Boolean> deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " numaralı kişi mevcut değildir."));
        customerRepository.delete(customer);
        Map<String, Boolean> response = new Hashtable<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
