package com.example.restapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Seller {
	
	protected Seller()
	{
		
	}
	
	@Id
	@GeneratedValue
	private int id;
	
//
	private String name;
	
	private String dob;
	
	@OneToMany(mappedBy = "seller")
	@JsonIgnore
	private List<Articles> Articles;
	
	public Seller(int id, String name, String dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	//private List<Articles> Articles;
//	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public List<Articles> getArticles() {
		return Articles;
	}
	
	public void setArticles(List<Articles> articles) {
		Articles = articles;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	
	
}
