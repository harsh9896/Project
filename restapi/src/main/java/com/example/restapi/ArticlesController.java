package com.example.restapi;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class ArticlesController {
	
	ArticlesRepository articlesRepository;
	SellerRepository sellerRepository;
	CustomerRepository customerRepository;
	@Autowired
	ArticleService articleService;
	public ArticlesController(ArticlesRepository articlesRepository, SellerRepository sellerRepository, CustomerRepository customerRepository) {
		super();
		this.articlesRepository = articlesRepository;
		this.sellerRepository = sellerRepository;
		this.customerRepository=customerRepository;
	}

	@GetMapping("/test")
	public String test()
	{
		return "Harsh";
	}
	
	@GetMapping("/Articles")
	public List<Articles> allArticles()
	{
		return articlesRepository.findAll();
	}
	
	@GetMapping("/Sellers/{id}/Articles")
	public List<Articles> allAuthorArticles(@PathVariable int id)
	{
		Optional<Seller> seller = sellerRepository.findById(id);
		if(seller.isEmpty())
		{
			
		}
		else
		{
			return seller.get().getArticles();
		}
		return null;
	}
	
	@GetMapping("/Sellers/{id}/Articles/{id2}")
	public Articles singleArticle(@PathVariable int id, @PathVariable int id2)
	{
		Optional<Seller> seller = sellerRepository.findById(id);
		if(seller.isEmpty())
		{
			
		}
		else
		{
			if(articleService.owns(id,id2))
			{
				return articlesRepository.findById(id2).get();
			}
			
		}
		return null;
	}
	
	@PostMapping("/Sellers/{id}/Articles")
	public Articles addArticles(@Valid @RequestBody Articles article, @PathVariable int id)
	{
		Optional<Seller> seller = sellerRepository.findById(id);
		if(seller.isEmpty())
		{
			
		}
		else
		{
			article.setSeller(seller.get());
			//System.out.println(article);
			articlesRepository.save(article);
			return article;
		}		
		return article;
	}
	@DeleteMapping("/Sellers/{id}/Articles/{id2}")
	public Articles deleteArticle(@PathVariable int id, @PathVariable int id2)
	{
		Optional<Seller> sellerFind = sellerRepository.findById(id);
		if(sellerFind.isEmpty())
		{
			
		}
		else
		{
			//article.setCustomer(customer.get());
			Optional<Articles> articleFind= articlesRepository.findById(id2);
			//System.out.println(articleFind.get());
			if(articleFind.isEmpty()||!articleService.owns(id, id2))
			{
				
			}
			else
			{
				articlesRepository.deleteById(id2);
				return articleFind.get();
			}
		}		
		return null;
		
	}
	
	@GetMapping("/Customers/{id}/Articles")
	public List<Articles> allCustomerArticles(@PathVariable int id)
	{
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty())
		{
			
		}
		else
		{
			return customer.get().getArticles();
		}
		return null;
	}
	
	@GetMapping("/Customers/{id}/Articles/{id2}")
	public Articles singleCustomerArticle(@PathVariable int id, @PathVariable int id2)
	{
		Optional<Customer> customer =customerRepository.findById(id);
		if(customer.isEmpty())
		{
			
		}
		else
		{
			if(articleService.bought(id,id2))
			return articlesRepository.findById(id2).get();
		}
		return null;
	}
	
	@PutMapping("/Customers/{id}/Articles/{id2}")
	public Articles addCustomerArticles(@PathVariable int id, @PathVariable int id2)
	{
		Optional<Customer> customerFind = customerRepository.findById(id);
		if(customerFind.isEmpty())
		{
			
		}
		else
		{
			//article.setCustomer(customer.get());
			Customer customer=customerFind.get();
			Optional<Articles> articleFind= articlesRepository.findById(id2);
			if(articleFind.isEmpty())
			{
				
			}
			else
			{
				Articles article = articleFind.get();
				
				List<Customer> customerList = article.getCustomers();
				
				customerList.add(customer);
				
				article.setCustomers(customerList);
				//System.out.println(customer);
				return articlesRepository.save(article);
			}
		}		
		return null;
	}
	@DeleteMapping("/Customers/{id}/Articles/{id2}")
	public Articles deleteCustomerArticle(@PathVariable int id, @PathVariable int id2)
	{
		Optional<Customer> customerFind = customerRepository.findById(id);
		if(customerFind.isEmpty())
		{
			
		}
		else
		{
			//article.setCustomer(customer.get());
			Customer customer=customerFind.get();
			Optional<Articles> articleFind= articlesRepository.findById(id2);
			if(articleFind.isEmpty()||!articleService.bought(id, id2))
			{
				
			}
			else
			{
				Articles article = articleFind.get();
				
				List<Customer> customerList = article.getCustomers();
				
				customerList= articleService.removeCustomer(id2, customerList);		
				
				article.setCustomers(customerList);
				System.out.println(customer);
				return articlesRepository.save(article);
			}
		}		
		return null;
		//articlesRepository.deleteById(id2);
	}
	
}
