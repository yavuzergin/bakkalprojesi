package org.deneme.bakkal.controller;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.deneme.bakkal.service.ProductService;
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
    @Autowired
    private ProductService productService;

    @GetMapping("/list-product")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/add-product")
    public Product createProduct(@RequestBody Product product) {
       return productService.create(product);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.update(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/remove-product/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable Long id) {
        Map<String, Boolean> response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }
}
