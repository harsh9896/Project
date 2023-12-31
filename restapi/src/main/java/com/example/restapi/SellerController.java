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
public class SellerController {
	
	private SellerRepository sellerRepository;

	public SellerController(SellerRepository sellerRepository) {
		super();
		this.sellerRepository = sellerRepository;
	}
	
	@GetMapping("/Sellers")
	public List<Seller> allSellers()
	{
		return sellerRepository.findAll();
	}
	
	@GetMapping("/Sellers/{username}")
	public Optional<Seller> allSellers(@PathVariable String username)
	{
		return sellerRepository.findByName(username);
	}
	
	
	@PostMapping("/Sellers")
	public Seller addSeller(@Valid @RequestBody Seller seller)
	{
		sellerRepository.save(seller);
		return seller;
	}

}
