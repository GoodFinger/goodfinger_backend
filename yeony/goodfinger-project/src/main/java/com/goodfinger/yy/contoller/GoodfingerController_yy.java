package com.goodfinger.yy.contoller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goodfinger.yy.repository.Company;
import com.goodfinger.yy.repository.CompanyRepository;
import com.goodfinger.yy.service.GoodfingerService;
import com.goodfinger.yy.service.GoodfingerServiceImpl;

@RestController
@RequestMapping("/com")
public class GoodfingerController_yy {
	Logger log = LoggerFactory.getLogger(GoodfingerController_yy.class);

	@Resource(name = "GoodfingerService")
	private GoodfingerService service ;
	
	@Value("${upload.path}")
	private String filePathRoot;
	
	@CrossOrigin(origins = "*")
	@GetMapping("company")
	public ResponseEntity<Company> getCompany(@RequestParam(value = "comId") String comId) throws Exception{
		log.debug("getCompany start.");
		log.debug("comId:'" + comId + "'");
		
		Company com = service.getCompanyByComId(comId);
		if(com == null){
			return new ResponseEntity<Company>(com, HttpStatus.NO_CONTENT);
		}
		log.debug("getCompany end.");
		return new ResponseEntity<Company>(com, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("companys")
	public ResponseEntity getCompanys(@RequestParam(value = "masterId") String masterId) throws Exception{
		log.debug("getCompanys start.");
		log.debug("masterId:'" + masterId + "'");
		
		List<Company> coms = service.getCompanyByMasterId(masterId);
		
		if(coms == null){
			return new ResponseEntity(coms, HttpStatus.NO_CONTENT);
		}
		
		log.debug("getCompanys end.");
		return new ResponseEntity(coms, HttpStatus.OK);
	}

	
	/**
	 * 
	 * @param request
	 * {
			"comid":"yycom2",
			"location":"서울시강남구",
			"mastername":"박예연",
			"name":"예연회사2"
		}
	 * @param comstring
	 * @param files
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@CrossOrigin(origins = "*")
	@PostMapping(value="insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity insertCompany(HttpServletRequest request, @RequestParam String company, 
			@RequestParam(value="files", required = false) ArrayList<MultipartFile> files) {
		if (company == null) {
			new ResponseEntity("null", HttpStatus.GONE);
		}
		JSONObject param = new JSONObject();
		param.put("comString", company);
		param.put("files", files);
		param.put("filePathRoot", filePathRoot);
		
		System.out.println("request" + request);
		System.out.println("comString" + company);
		
		String returnStatus = "";
		
		try {
			returnStatus = service.insertCompany(param);
			if(returnStatus == null){
				new ResponseEntity("error", HttpStatus.EXPECTATION_FAILED);
			}
		} catch ( Exception  e) {
			e.printStackTrace();
			new ResponseEntity("error", HttpStatus.EXPECTATION_FAILED);
		} 
		
		return new ResponseEntity(returnStatus, HttpStatus.OK);
	}	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins = "*")
	@PostMapping(value="update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateCompany(HttpServletRequest request, @RequestParam String company, 
			@RequestParam(value="files", required = false) ArrayList<MultipartFile> files,
			@RequestParam String comId) {
		if (company == null ) {
			new ResponseEntity<String>("null", HttpStatus.GONE);
		}
		JSONObject param = new JSONObject();
		param.put("comString", company);
		param.put("files", files);
		param.put("filePathRoot", filePathRoot);
		param.put("comId", comId);
		
		log.debug("request:" + request);
		System.out.println("request" + request);
		System.out.println("company" + company);
		
		String returnStatus = "";
		
		try {
			returnStatus = service.updateCompany(param);
			if(returnStatus.equalsIgnoreCase("error")){
				new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
			} 
		} catch ( Exception  e) {
			e.printStackTrace();
			new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		} 
		
		return new ResponseEntity<String>(returnStatus, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value="delete")
	public ResponseEntity<String> deleteCompany(HttpServletRequest request,	@RequestParam String comId) {
		if (comId == null ) {
			new ResponseEntity<String>("null", HttpStatus.GONE);
		}
		
		log.debug("request:" + request);
		System.out.println("request" + request);
		
		String returnStatus = "";
		
		try {
			returnStatus = service.deleteCompany(comId);
			if(returnStatus.equalsIgnoreCase("error")){
				new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
			}
			returnStatus = "ok";
		} catch ( Exception  e) {
			e.printStackTrace();
			new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		} 
		
		return new ResponseEntity<String>(returnStatus, HttpStatus.OK);
	}
}
