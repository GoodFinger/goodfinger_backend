package com.goodfinger.yy;

import java.util.Iterator;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import com.goodfinger.yy.repository.User;
import com.goodfinger.yy.repository.UserRepository;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class})

public class GoodfingerProjectApplication implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(GoodfingerProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(repository.count());
		System.out.println("-------------------------------");
		for (User user : repository.findAll()) {
	      System.out.println(user.toString());
	    }
		System.out.println("Users found with findAll();");
		
	}

}
