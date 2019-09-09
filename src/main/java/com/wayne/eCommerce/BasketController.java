package com.wayne.eCommerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@RequestMapping("basket")

public class BasketController {

	private BasketRepository brepo;
	private ProductRepository prepo;

	public BasketController(BasketRepository brepo, ProductRepository prepo) {
		this.brepo = brepo;
		this.prepo = prepo;
	}

	// TODO list appropriate message if there are no users with that name
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/{id}")

	public Basket getBasket(@PathVariable Integer id) {
		return brepo.findById(id).get();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(path = "/{id}")

	public Basket updateBasket(@PathVariable Integer id, @RequestBody Basket basketDetails) {
		for (Product p : basketDetails.getProducts()) {
			//if product already exists then update it's list of baskets to include this new one
			if (prepo.existsByName(p.getName())) {
				Product prodz = prepo.findByName(p.getName());
				Integer pid = prodz.getId();
				p.setId(pid);
				Set<Basket> baskets = p.getBaskets();
				baskets.add(brepo.findById(id).get());
				p.setBaskets(baskets);
				if (p.getPrice() == null) {
					p.setPrice(prodz.getPrice());
				}
				
			//if product doesn't exist then create a new basket for it and then create the product
			} else {
				Set<Basket> baskets = new HashSet<Basket>();
				baskets.add(brepo.findById(id).get());
				p.setBaskets(baskets);
				prepo.save(p);
			}
		}
		Basket basket = brepo.findById(id).get();
		basketDetails.setId(id);
		basket.setProducts(basketDetails.getProducts());
		return	brepo.save(basket);
		
	}

	// TODO confirm whether a user with that name exists and if not let user know
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping(path = "/{id}")
	@Transactional
	public Iterable<Basket> deleteBasket(@PathVariable Integer id) {
		if (brepo.existsById(id)) {
			brepo.deleteById(id);
		}
		return brepo.findAll();
	}
}