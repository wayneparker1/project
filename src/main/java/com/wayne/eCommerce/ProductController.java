package com.wayne.eCommerce;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//TODO UPDATE THE REQUEST MAPPING
@RequestMapping("products")

public class ProductController {

	private ProductRepository repo;

	public ProductController(ProductRepository repo) {
		this.repo = repo;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping

	public Iterable<Product> getProducts() {
		return repo.findAll();

	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/{name}")

	public Product getProduct(@PathVariable String name) {
		return repo.findByName(name);
	}


	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping()
	//TODO throw exception when product already exists
	public Product createProduct(@RequestBody Product productDetails) {
		if (repo.existsByName(productDetails.getName())) {
			return repo.findByName(productDetails.getName());
		}
		return productDetails = repo.save(productDetails);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(path = "/{name}")

	public Product updateProduct(@PathVariable String name, @RequestBody Product productDetails) {
		Product product = repo.findByName(name);
		if (product.getName().equals(productDetails.getName())) {
			productDetails.setId(product.getId());
			return repo.save(productDetails);
		}
		return product;
	}

	// TODO confirm whether a user with that name exists and if not let user know
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void deleteProduct(@PathVariable Integer id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
		}

	}
}
