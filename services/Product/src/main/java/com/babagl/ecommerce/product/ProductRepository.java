package com.babagl.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> finAllByIdInOrderById(List<Integer> productIds);
}
