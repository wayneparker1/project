package com.wayne.eCommerce;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL) 
	@PrimaryKeyJoinColumn 
	private Basket basket;
	
	@Column
	private String userName;

	@Column
	private String password;
	
	
	
	public Basket getBasket() { return basket; }
	  
	public void setBasket(Basket basket) { this.basket = basket; }
	 

	public User() {
		
	}
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("{%d,%s,%s}", this.id, this.userName, this.password);
	}

}
