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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "basket")
public class Basket {
	
	@Id
	@Column(name = "id")  
	@JsonView(Views.Public.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(Views.Internal.class)
	@OneToOne(mappedBy = "basket")
	@JsonBackReference
    private User user;
	
	@Column
	@JsonView(Views.Internal.class)
	@ManyToMany(cascade= {CascadeType.ALL})
	//@JsonManagedReference
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "id")
	private Set<Product> products = new HashSet<Product>();
	
	@Transient
	private ProductRepository repo;
	
	
	public User getUser() { return user; }
	  
	public void setUser(User user) { this.user = user; }
	 
	
	public Set<Product> getProducts() {
		return products;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getBasket_id() {
		return id;
	}

	public void setBasket_id(Integer id) {
		this.id = id;
	}

	public Basket() {
		
	}
	

	@Override
	public String toString() {
		return String.format("{%d}", this.id);
	}

}

