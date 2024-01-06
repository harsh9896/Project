package com.example.restapi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Optional<Customer> findByName(String name);
	
}
