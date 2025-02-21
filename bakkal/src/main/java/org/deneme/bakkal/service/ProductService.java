package org.deneme.bakkal.service;

import org.deneme.bakkal.exception.ResourceNotFoundException;
import org.deneme.bakkal.model.Product;
import org.deneme.bakkal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " numaralı ürün mevcut değildir."));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " numaralı ürün mevcut değildir."));
        product.setProductName(productDetails.getProductName());
        product.setProductPrice(productDetails.getProductPrice());
        return productRepository.save(product);
    }

    public Map<String, Boolean> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı ürün bulunamadı."));
        productRepository.delete(product);
        Map<String, Boolean> response = new Hashtable<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
