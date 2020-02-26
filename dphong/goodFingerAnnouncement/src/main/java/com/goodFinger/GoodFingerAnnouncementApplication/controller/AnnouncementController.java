package com.goodFinger.GoodFingerAnnouncementApplication.controller;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodFinger.GoodFingerAnnouncementApplication.service.AnnouncementServiceImpl;

@RestController
//@RequestMapping("/announcement")
public class AnnouncementController {
	
	@Resource(name = "AnnouncementService")
	private AnnouncementServiceImpl announcementServiceImpl;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "helloWorld";
	}
	
	@GetMapping(path = "/getHello")
	public String helloSpringWorld() {
		return announcementServiceImpl.testFunc();
	}
}