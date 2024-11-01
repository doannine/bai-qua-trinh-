package com.example.unittest.demo.service;

import com.example.unittest.demo.model.Product;
import com.example.unittest.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct_NullProduct() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(null);
        });
        assertEquals("Product name cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testAddProduct_EmptyName() {
        Product product = new Product();
        product.setName("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(product);
        });
        assertEquals("Product name cannot be null or empty", exception.getMessage());
    }



    @Test
    public void testUpdateProduct_NullProduct() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.updateProduct(1L, null);
        });
        assertEquals("Product name cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testUpdateProduct_ProductNotFound() {
        Product product = new Product();
        product.setName("Updated Product");

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.updateProduct(1L, product);
        });
        assertEquals("Product not found", exception.getMessage());
    }



    @Test
    public void testDeleteProduct_ProductNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.deleteProduct(1L);
        });
        assertEquals("Product not found", exception.getMessage());
    }


}
