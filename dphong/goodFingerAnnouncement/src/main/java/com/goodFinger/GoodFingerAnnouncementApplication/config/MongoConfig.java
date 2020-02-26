package com.goodFinger.GoodFingerAnnouncementApplication.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	@Value("${spring.data.mongodb.username}")
	private String userName;
	
	@Value("${spring.data.mongodb.password}")
	private String password;
	
	@Value("${spring.data.mongodb.database}")
	private String database;

	@Override
	public MongoClient mongoClient() {
		MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
		return new MongoClient(new ServerAddress("127.0.0.1", 17017), Arrays.asList(credential));
	}

	@Override
	protected String getDatabaseName() {
		return database;
	}
	
}