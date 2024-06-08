package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find products by category name
    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);
    List<Product> findByCategory_Name(String categoryName);

    // Search products by name containing a specific string
    List<Product> findByNameContainingIgnoreCase(String name);

    // Fetch all products with their category details
    // If using custom query
    @Query("SELECT p FROM Product p JOIN p.category c")
    List<Product> findAllWithCategory();
}
