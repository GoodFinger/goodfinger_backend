package com.goodfinger.yy.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
	
	public User findByName(String name);
	public List<User> findAllByName(String name);
	
}
