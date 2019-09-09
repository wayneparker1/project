package com.wayne.eCommerce;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUserName(String userName);

	public boolean existsByUserName(String userName);
}
