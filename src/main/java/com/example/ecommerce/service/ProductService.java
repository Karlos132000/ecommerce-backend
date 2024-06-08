package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
}