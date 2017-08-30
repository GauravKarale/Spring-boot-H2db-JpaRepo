package com.av.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av.shopping.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
		
}
