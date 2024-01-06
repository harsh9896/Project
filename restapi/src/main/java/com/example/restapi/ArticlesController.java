package com.example.restapi;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
//@CrossOrigin
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
	
	@GetMapping("/Articles/{id}")
	public Articles getArticle(@PathVariable int id)
	{
		return articlesRepository.findById(id).get();
	}
	
	@GetMapping("/Sellers/{username}/Articles")
	public List<Articles> allAuthorArticles(@PathVariable String username)
	{
		Optional<Seller> seller = sellerRepository.findByName(username);
		if(seller.isEmpty())
		{
			
		}
		else
		{
			return seller.get().getArticles();
		}
		return null;
	}
	
	@GetMapping("/Sellers/{username}/Articles/{id}")
	public Articles singleArticle(@PathVariable String username, @PathVariable int id)
	{
		Optional<Seller> seller = sellerRepository.findByName(username);
		if(seller.isEmpty())
		{
			
		}
		else
		{
			if(articleService.owns(username,id))
			{
				return articlesRepository.findById(id).get();
			}
			
		}
		return null;
	}
	
	@PostMapping("/Sellers/{username}/Articles")
	public Articles addArticles(@Valid @RequestBody Articles article, @PathVariable String username)
	{
		Optional<Seller> seller = sellerRepository.findByName(username);
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
	@PutMapping("/Sellers/{username}/Articles/{id}")
	public Articles updateArticles(@Valid @RequestBody Articles article, @PathVariable String username, @PathVariable int id)
	{
		Optional<Seller> seller = sellerRepository.findByName(username);
		if(seller.isEmpty())
		{
			
		}
		else
		{
			//article.setCustomer(customer.get());
			Optional<Articles> articleFind= articlesRepository.findById(id);
			//System.out.println(articleFind.get());
			if(articleFind.isEmpty()||!articleService.owns(username, id)||article.getId()!=id)
			{
				
			}
			else
			{
				article.setSeller(articleFind.get().getSeller());
				article.setCustomers(articleFind.get().getCustomers());
				return articlesRepository.save(article);
			}
		}		
		return null;
	}
	@DeleteMapping("/Sellers/{username}/Articles/{id}")
	public Articles deleteArticle(@PathVariable String username, @PathVariable int id)
	{
		Optional<Seller> sellerFind = sellerRepository.findByName(username);
		if(sellerFind.isEmpty())
		{
			
		}
		else
		{
			//article.setCustomer(customer.get());
			Optional<Articles> articleFind= articlesRepository.findById(id);
			//System.out.println(articleFind.get());
			if(articleFind.isEmpty()||!articleService.owns(username, id))
			{
				
			}
			else
			{
				articlesRepository.deleteById(id);
				return articleFind.get();
			}
		}		
		return null;
		
	}
	
	@GetMapping("/Customers/{username}/Articles")
	public List<Articles> allCustomerArticles(@PathVariable String username)
	{
		Optional<Customer> customer = customerRepository.findByName(username);
		if(customer.isEmpty())
		{
			
		}
		else
		{
			return customer.get().getArticles();
		}
		return null;
	}
	
	@GetMapping("/Customers/{username}/Articles/{id}")
	public Articles singleCustomerArticle(@PathVariable String username, @PathVariable int id)
	{
		Optional<Customer> customer =customerRepository.findByName(username);
		if(customer.isEmpty())
		{
			
		}
		else
		{
			if(articleService.bought(username,id))
			return articlesRepository.findById(id).get();
		}
		return null;
	}
	
	@PutMapping("/Customers/{username}/Articles/{id}")
	public Articles addCustomerArticles(@PathVariable String username, @PathVariable int id)
	{
		Optional<Customer> customerFind = customerRepository.findByName(username);
		if(customerFind.isEmpty())
		{
			
		}
		else
		{
			//article.setCustomer(customer.get());
			Customer customer=customerFind.get();
			Optional<Articles> articleFind= articlesRepository.findById(id);
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
	@DeleteMapping("/Customers/{username}/Articles/{id}")
	public Articles deleteCustomerArticle(@PathVariable String username, @PathVariable int id)
	{
		Optional<Customer> customerFind = customerRepository.findByName(username);
		if(customerFind.isEmpty())
		{
			
		}
		else
		{
			//article.setCustomer(customer.get());
			Customer customer=customerFind.get();
			Optional<Articles> articleFind= articlesRepository.findById(id);
			if(articleFind.isEmpty()||!articleService.bought(username, id))
			{
				
			}
			else
			{
				Articles article = articleFind.get();
				
				List<Customer> customerList = article.getCustomers();
				
				customerList= articleService.removeCustomer(id, customerList);		
				
				article.setCustomers(customerList);
				System.out.println(customer);
				return articlesRepository.save(article);
			}
		}		
		return null;
		//articlesRepository.deleteById(id2);
	}
	
}
