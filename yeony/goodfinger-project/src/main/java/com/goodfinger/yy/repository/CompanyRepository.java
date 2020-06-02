package com.goodfinger.yy.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
	public Company findByName(String name);
	public List<Company> findAllByName(String name);
	public List<Company> findByMasterId(String masterId);
}
