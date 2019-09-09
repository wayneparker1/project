package com.wayne.eCommerce;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("users")

public class UserController {

	private UserRepository repo;

	public UserController(UserRepository repo) {
		this.repo = repo;
	}

	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping

	public Iterable<User> getUsers() {
		
		Iterable<User> users;
		users = repo.findAll();
		return users;

	}
	
	// TODO list appropriate message if there are no users with that name
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/{userName}")

	public User getUser(@PathVariable String userName) {

		return repo.findByUserName(userName);

	}


	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping()

	public User createUser(@RequestBody User userDetails) {
		
		if (repo.existsByUserName(userDetails.getUserName())) {
			return repo.findByUserName(userDetails.getUserName());
		}
		Basket basket = new Basket();
		User user = repo.save(userDetails);
		basket.setId(userDetails.getId());
		user.setBasket(basket);
		return repo.save(user);

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(path = "/{userName}")

	public User updateUser(@PathVariable String userName, @RequestBody User userDetails) {
		User user = repo.findByUserName(userName);
		userDetails.setBasket(user.getBasket());
		userDetails.setId(user.getId());
		return repo.save(userDetails);

	}

	// TODO confirm whether a user with that name exists and if not let user know
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void deleteUser(@PathVariable Integer id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
		}

	}
}
