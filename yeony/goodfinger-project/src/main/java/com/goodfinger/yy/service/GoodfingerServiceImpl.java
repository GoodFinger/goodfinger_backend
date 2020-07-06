package com.goodfinger.yy.service;

 import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodfinger.yy.dao.GoodfingerDao;
import com.goodfinger.yy.repository.Company;
import com.goodfinger.yy.repository.CompanyRepository;

@Component("GoodfingerService")
@Service
public class GoodfingerServiceImpl implements GoodfingerService{
	Logger log = LoggerFactory.getLogger(GoodfingerServiceImpl.class);

	@Autowired
	CompanyRepository repositoryCom;
	
	@Autowired
	GoodfingerDao dao;
	
	@Value("${upload.path}")
	private String filePathRoot;
	
	@Value("${companyDB.path}")
	private String dbFilePath;
	
	@Override
	public List<Company> getCompanyAll() {
		List<Company> com = repositoryCom.findAll();
		return com;
	}
	
	@Override
	public Company getCompanyByComId(String comId) {
		Optional<Company> coms = repositoryCom.findById(comId);
		Company com = coms.get();
		return com;
	}
	
	@Override
	public List<Company> getCompanyByMasterId(String masterId) {
		List<Company> coms = repositoryCom.findByMasterId(masterId);
		return coms;
	}
	
	@Override
	public String insertCompany(JSONObject param) {
		String comstring = param.get("comString").toString();
		ArrayList<MultipartFile> files = (ArrayList<MultipartFile>) param.get("files");
		String filePathRoot = param.get("filePathRoot").toString();
		
		Company com = new Company();
		String returnStatus = "error";
		
		try {
			JSONParser jsonpar = new JSONParser();
			JSONObject insertCom = (JSONObject) jsonpar.parse(comstring);
			com.setLocation(insertCom.get("location").toString());
			com.setMastername(insertCom.get("mastername").toString());
			com.setMasterId(insertCom.get("masterId").toString());
			com.setName(insertCom.get("name").toString());
			
			List<String> filePathAll = new ArrayList<String>();
			filePathAll = fileUpload(files);
			com.setPicture(filePathAll);
			
			dao.addCompany(com);
			returnStatus = "ok";
			
		} catch ( Exception  e) {
			returnStatus = "error";
			e.printStackTrace();
		} 

		return returnStatus;
	}
	

	@Override
	public String insertCompany() throws Exception {
		Company com = new Company();
//		log.debug("start insertCompany.");
//		com.setcomid("yycom");
//		com.setLocation("위치");
//		com.setMastername("박예연");
//		com.setName("예연회사");
//		com.setPicture("사진여러개들어갈건데");
		repositoryCom.save(com);
		log.debug("end insertCompany.");
		return "";
	}

	@Override
	public String updateCompany(JSONObject param) {
		String comstring = param.get("comString").toString();
		String comId = param.get("comId").toString();
		ArrayList<MultipartFile> files = (ArrayList<MultipartFile>) param.get("files");
		String filePathRoot = param.get("filePathRoot").toString();
		
		Company com = new Company();
		String returnStatus = "error";
		
		try {
			JSONParser jsonpar = new JSONParser();
			JSONObject updateCom = (JSONObject) jsonpar.parse(comstring);
			System.out.println(comId);
			Company oldCom2 = dao.findByCompanyId(comId);
			
			com.setId(oldCom2.getId());
			com.setLocation(updateCom.get("location").toString());
			com.setName(updateCom.get("name").toString());
			com.setMasterId(updateCom.get("masterId").toString());
			com.setMastername(updateCom.get("mastername").toString());
			
			List<String> filePathAll = new ArrayList<String>();
			
			if(files.size() > 0){
				filePathAll = fileUpload(files);
				if(filePathAll.get(0).equalsIgnoreCase("error")){
					new Throwable();
				}
			}
			
			if(oldCom2.getPicture().size() > 0){
				fileDelete((ArrayList<String>)oldCom2.getPicture());
			}
			
			com.setPicture(filePathAll);
			
			dao.updateCompany(com);
			returnStatus = "ok";
			
		} catch ( Exception  e) {
			returnStatus = "error";
			e.printStackTrace();
		} 
		return returnStatus;
	}
	
	// autoWired X file upload 
	private List<String> fileUpload(ArrayList<MultipartFile> files){
		System.out.println("fileUpload START.");
		List<String> makeFile = new ArrayList<String>();
		ArrayList<String> successFile = new ArrayList<>(); 
		
		
		for(int i=0; i <files.size(); i++){
			
			UUID fileUuid = UUID.randomUUID();
			
			// uuid.jpg
			String uuidPath = fileUuid.toString() + "." + files.get(i).getOriginalFilename().split("\\.")[1];
			
			// /home/files/companyfile/uuid.jpg
			// filepathroot = /home/files/companyfile/
			String filePath = filePathRoot + uuidPath;
			
			// /files/companyfile/uuid.jpg
			String dbFilePathFullName = dbFilePath + uuidPath;
			System.out.println(dbFilePathFullName);
			
			File fileData = new File(filePath);
			try {
				files.get(i).transferTo(fileData);
			} catch (Exception e) {
				String x = "error";
				successFile.clear();
				successFile.add("error");
				return successFile;
			}
			makeFile.add(dbFilePathFullName);
			successFile.add(filePath);
			System.out.println("success.");
		}
		System.out.println("fileUpload END.");
		return makeFile; 
	}
	
	private String fileDelete(ArrayList<String> files) {
		System.out.println("fileDelete START");
		String returnStatus = "error";
		try {
			for(String filePath : files){
				File fileData = new File(filePath);
				if(fileData.exists()){
					if(fileData.delete()){
						log.debug("success delete file " + filePath);
					} else {
						log.debug("fail delete file " + filePath);
					}
				} else {
					log.debug("notExists file " + filePath);
				}
				returnStatus = "ok";
			}
		} catch (Exception e) {
			returnStatus = "error";
		}
		
		System.out.println("fileDelete END.");
		return returnStatus; 
	}

	// delete시에 파일들도 함께 지워야 함.
	@Override
	public String deleteCompany(String comId) {
		System.out.println("deleteCompany START.");
		String returnStatus = "error";
		
		try {
			Company com = dao.findByCompanyId(comId);
			
			ArrayList<String> pictureList = (ArrayList<String>)com.getPicture();
			String deleteStatus = "error";
			
			if(pictureList.size() > 0 ){
				deleteStatus = fileDelete(pictureList);
				if(deleteStatus.equalsIgnoreCase("error")){
					System.out.println("파일 삭제중 에러발생.");
					return returnStatus;	
				}
			}
			
			dao.deleteCompany(comId);
			returnStatus = "ok";
			
		} catch (Exception e) {
			returnStatus = "error";
			e.printStackTrace();
			return returnStatus;
		}
		
		System.out.println("deleteCompany END.");
		return returnStatus;
	}
}
