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
@RequestMapping("admin")

public class AdminController {

	private AdminRepository repo;

	public AdminController(AdminRepository repo) {
		this.repo = repo;
	}

	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping

	public Iterable<AdminUser> getAdmins() {
		
		Iterable<AdminUser> users;
		users = repo.findAll();
		return users;

	}
	
	// TODO list appropriate message if there are no users with that name
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/{userName}")

	public AdminUser getAdmin(@PathVariable String userName) {

		return repo.findByUserName(userName);

	}


	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping()

	public AdminUser createAdmin(@RequestBody AdminUser userDetails) {
		
		if (repo.existsByUserName(userDetails.getUserName())) {
			return repo.findByUserName(userDetails.getUserName());
		}
		return repo.save(userDetails);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(path = "/{userName}")

	public AdminUser updateUser(@PathVariable String userName, @RequestBody AdminUser userDetails) {
		AdminUser user = repo.findByUserName(userName);
		userDetails.setId(user.getId());
		repo.save(userDetails);
		return userDetails;

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
