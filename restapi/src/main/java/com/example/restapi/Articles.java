package com.example.restapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Articles {

	@Id
	@GeneratedValue
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name = "articles_id")
	private int id;

	private int price;
	
	@Size(min=5, message="Enter descrption with lenght atleast 5")
	private String description;

	private String name;
	
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Seller seller;
	
	@ManyToMany
	@JsonIgnore
	private List<Customer> customers;
//	public Articles(int id, int price, String description, String name, Seller seller) {
//		super();
//		this.id = id;
//		this.price = price;
//		this.description = description;
//		this.name = name;
//		this.seller = seller;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Articles [id=" + id + ", price=" + price + ", description=" + description + ", name=" + name + "]";
	}

}
