package org.deneme.bakkal.controller;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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

import org.deneme.bakkal.model.Product;
import org.deneme.bakkal.exception.ResourceNotFoundException;
import org.deneme.bakkal.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product-api/")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list-product")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " numaralı ürün mevcut değildir."));
        return ResponseEntity.ok(product);
    }
    @PostMapping("/add-product")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " numaralı ürün mevcut değildir."));
        product.setProductName(productDetails.getProductName());
        product.setProductPrice(productDetails.getProductPrice());

       Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/remove-product/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " numaralı ürün mevcut değildir."));
        productRepository.delete(product);
        Map<String, Boolean> response = new Hashtable<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
