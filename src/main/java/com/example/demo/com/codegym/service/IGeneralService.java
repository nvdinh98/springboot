package com.example.demo.com.codegym.service;

import com.example.demo.com.codegym.model.Customer;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<Customer> findAll();

    Optional<Customer> findById(Long id);

    void save(T t);

    void remove(Long id);
}
