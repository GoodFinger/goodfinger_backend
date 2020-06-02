package com.goodfinger.yy.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodfinger.yy.repository.User;
import com.goodfinger.yy.repository.UserRepository;


@RestController
@RequestMapping("/")
public class MainController {
	
//	@GetMapping("yy")
//    public ModelAndView root_test() throws Exception{
//		System.out.println("여기 오는지 테스으");
//        return new ModelAndView("index/index");
//    }

	@Autowired
	private UserRepository repository;
	
	@CrossOrigin(origins = "*")
	@GetMapping("yy")
    public ResponseEntity root_test() throws Exception{
		System.out.println("yytest Success.");

		String userData = "";
		List<User> userData2 = repository.findAll();
		for (User user : repository.findAll()) {
	      System.out.println(user.toString());
	      userData += user.toString();
	    }
		System.out.println();
		System.out.println(repository.findByName("예연2"));
		return new ResponseEntity(userData2, HttpStatus.OK);
    }
	
	@RequestMapping("getparam")
	public String getparam(Model model, @RequestParam (value = "name") String name) throws Exception{
		Logger log = LoggerFactory.getLogger(MainController.class);
		String resultName = "name is " + name;
		return resultName;
	}
}
