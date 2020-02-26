package com.goodfinger.yy;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="User")
public class User {
	
	@Id 
	private String id;
	private String name;
	private String sex;
	
	public User(){}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public User(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}
	@Override
	public String toString() {
		return String.format("User[name='%s', sex='%s']", name,sex);
	}
}
