package com.sk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.entity.Customer;

public interface ConstomerRepo extends JpaRepository<Customer, Long> {

}
