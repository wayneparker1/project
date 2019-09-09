package com.wayne.eCommerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ECommerceApplication.class, args);
		AdminRepository adminRepository = ctx.getBean(AdminRepository.class);
		BasketRepository basketRepository = ctx.getBean(BasketRepository.class);
		ProductRepository productRepository = ctx.getBean(ProductRepository.class);
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		
		AdminUser admin = new AdminUser("admin", "pass");
		adminRepository.save(admin);
		
		Product product = new Product("orange", 5);
		productRepository.save(product);
		Product prod = new Product("banana", 10);
		productRepository.save(prod);
		User user = new User("bob", "bobby");
		userRepository.save(user);

		
		Product product1 = new Product("cheese", 15);
		Product product2 = new Product("sausage", 8);
		Product product3 = new Product("chicken", 22);
		Product product4 = new Product("lemon", 4);
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);
		Set<Product> prods = new HashSet<Product>();
		prods.add(product1);
		prods.add(product2);
		
		Basket basket = new Basket();
		basketRepository.save(basket);

		System.out.println("did it work");
	
	}

}
