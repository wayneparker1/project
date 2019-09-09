package com.wayne.eCommerce;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	public Product findByName(String name);
	public Boolean existsByName(String name);

}
