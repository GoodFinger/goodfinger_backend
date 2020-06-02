package com.goodfinger.yy.repository;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Company")
public class Company {
	private String id;
	private String name;
	private String location;
	private String mastername;
	private String masterId;
	private List<String> picture;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getMasterId() {
		return masterId;
	}
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	public List<String> getPicture() {
		return picture;
	}

	public void setPicture(List<String> picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return String.format("{name:'%s', location:'%s', mastername:'%s', picture:'%s'}", name,location,mastername,picture);
	}
}
