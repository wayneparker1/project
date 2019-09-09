package com.wayne.eCommerce;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminUser, Integer> {
	
	public AdminUser findByUserName(String userName);

	public boolean existsByUserName(String userName);

}
