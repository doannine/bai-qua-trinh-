package com.example.unittest.demo.service;

import com.example.unittest.demo.model.Product;
import com.example.unittest.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        Optional<Product> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException();
        }
        return optionalProduct.get();
    }
}
