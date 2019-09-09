package com.wayne.eCommerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "products")
public class Product {
	@Transient
	private BasketRepository brepo;
	@Transient
	private ProductRepository prepo;
	
	@Id
	@JsonView(Views.Internal.class)
	@Column(name = "id")  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(Views.Public.class)
	@Column
	private String name;
	
	@Column
	@JsonView(Views.Public.class)
	private Integer price;
	
	@Column
	@JsonView(Views.Internal.class)
	@ManyToMany(mappedBy = "products", cascade= {CascadeType.ALL})
	//@JsonBackReference
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "id")
	private Set<Basket> baskets = new HashSet<Basket>();

	public Product() {
		
	}

	
	public Product(String name, Integer price) {
		
		this.name = name;
		this.price = price;
		
	}
	
	public Product(String name, Integer price, Set<Basket> baskets) {
		
		this.name = name;
		this.price = price;
		this.baskets = baskets;
		
	}
	
	public Set<Basket> getBaskets() {

		return baskets;
	}

	public void setBaskets(Set<Basket> baskets) {

		this.baskets = baskets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.format("{%d,%s,%d}", this.id, this.name, this.price);
	}

}

