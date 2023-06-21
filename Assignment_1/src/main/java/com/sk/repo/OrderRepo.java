package com.sk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
