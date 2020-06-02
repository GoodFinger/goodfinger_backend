package com.goodfinger.yy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodfinger.yy.repository.Company;
import com.goodfinger.yy.repository.CompanyRepository;

@Repository
public class GoodfingerDao {
	@Autowired
	CompanyRepository repositoryCom;
	
	// insert:있으면 insert안함, save:있어도 덮어씀
	public void addCompany(Company com) throws Exception {
		repositoryCom.insert(com);
	}
	
	// update:있으면 update, save:덮어씀
	public void updateCompany(Company com) throws Exception {
		repositoryCom.save(com);
	}
	
	public Company findByCompanyId(String comId) throws Exception {
		return repositoryCom.findById(comId).get();
	}
	
	public void deleteCompany(String comId) throws Exception {
		repositoryCom.deleteById(comId);
	}
}
