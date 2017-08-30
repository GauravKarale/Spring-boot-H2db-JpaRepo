package com.av.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av.shopping.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
