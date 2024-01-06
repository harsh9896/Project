package com.example.restapi;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	
	ArticlesRepository articlesRepository;
	SellerRepository sellerRepository;
	CustomerRepository customerRepository;
	public ArticleService(ArticlesRepository articlesRepository, SellerRepository sellerRepository, CustomerRepository customerRepository) {
		super();
		this.articlesRepository = articlesRepository;
		this.sellerRepository = sellerRepository;
		this.customerRepository=customerRepository;
	}

	
	public Boolean bought(String customerName, int articleId)
	{
		List<Articles> articleList= customerRepository.findByName(customerName).get().getArticles();
		
		return articleList.stream().anyMatch(article-> article.getId()==articleId);
	}
	
	public List<Customer> removeCustomer(int articleId, List<Customer> customerList)
	{		
		return customerList.stream().filter(customer-> customer.getId()==articleId).collect(Collectors.toList());
	}


	public boolean owns(String sellerName, int articleId) {
		
		List<Articles> articleList= sellerRepository.findByName(sellerName).get().getArticles();
		return articleList.stream().anyMatch(article-> article.getId()==articleId);
	}
}