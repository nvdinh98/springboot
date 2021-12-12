package com.example.demo.com.codegym.repository;

import com.example.demo.com.codegym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
