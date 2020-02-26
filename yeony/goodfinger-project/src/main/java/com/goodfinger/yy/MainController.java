package com.goodfinger.yy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@Controller("index")
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
    public String root_test() throws Exception{
		System.out.println("yytest Success.");

		String userData = "";
		for (com.goodfinger.yy.User user : repository.findAll()) {
	      System.out.println(user.toString());
	      userData += user.toString();
	    }
		
        return userData;
    }
	
	@RequestMapping("getparam")
	public String getparam(Model model, @RequestParam (value = "name") String name) throws Exception{
		
		String resultName = "name is " + name;
		return resultName;
	}
}
