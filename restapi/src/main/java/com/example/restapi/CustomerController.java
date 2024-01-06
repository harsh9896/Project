package com.example.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
//@CrossOrigin
public class CustomerController {
	
	private CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	@GetMapping("/Customers")
	public List<Customer> allCustomers()
	{
		return customerRepository.findAll();
	}
	
	@GetMapping("/Customers/{username}")
	public Optional<Customer> allSellers(@PathVariable String username)
	{
		return customerRepository.findByName(username);
	}
	
	
	@PostMapping("/Customers")
	public Customer addSeller(@Valid @RequestBody Customer customer)
	{
		customerRepository.save(customer);
		return customer;
	}

}
