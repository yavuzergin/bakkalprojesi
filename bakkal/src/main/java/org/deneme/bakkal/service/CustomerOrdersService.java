package org.deneme.bakkal.service;

import org.deneme.bakkal.repository.CustomerOrdersRepository;
import org.deneme.bakkal.repository.CustomerRepository;
import org.deneme.bakkal.repository.ProductRepository;
import org.deneme.bakkal.SellProductRequest;
import org.deneme.bakkal.model.Customer;
import org.deneme.bakkal.model.Product;
import org.deneme.bakkal.model.CustomerOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.deneme.bakkal.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerOrdersService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    public CustomerOrders sellProduct(SellProductRequest input) {
        final Customer customer = customerRepository.findById(input.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getCustomerId() + " numaralı kişi mevcut değildir."));
        final Product product = productRepository.findById(input.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getProductId() + " numaralı ürün mevcut değildir."));
        final CustomerOrders customerOrder = new CustomerOrders();
        customerOrder.setCustomer(customer);
        customerOrder.setProduct(product);
        customerOrder.setQuantity(input.getQuantity());
        double totalCost = input.getQuantity() * product.getProductPrice();
        customerOrder.setTotal_cost(totalCost);
        return customerOrdersRepository.save(customerOrder);
    }

    public Map<String, Double> getAllCustomerOrders() {
        List<CustomerOrders> orders = customerOrdersRepository.findAll();
        Map<String, Double> orderSummary = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getCustomer().getFirstName(),
                        Collectors.summingDouble(order -> order.getProduct().getProductPrice() * order.getQuantity())
                ));
        return orderSummary;
    }

    public Double calculateCustomerDebth(Long id) {
        List<CustomerOrders> orders = customerOrdersRepository.findByCustomer_Id(id);
        double totalDebth = orders.stream()
                .collect(Collectors.summingDouble(order -> order.getProduct().getProductPrice() * order.getQuantity()));
        return totalDebth;
    }

    public Double calculateCustomerDebth2(Long id) {
        return customerOrdersRepository.retrieveDebthOfCustomer(id);
    }

    public List<String> getProductBuyers(Long id) {
        List<CustomerOrders> buyers = customerOrdersRepository.findByProduct_Id(id);
        List<String> productBuyers = buyers.stream()
                .map(buyer -> buyer.getCustomer().getFirstName() + " " + buyer.getCustomer().getLastName()).collect(Collectors.toList());
        return productBuyers;
    }
}
