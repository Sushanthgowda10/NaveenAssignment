package com.sk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
