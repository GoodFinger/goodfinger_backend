package com.goodFinger.GoodFingerAnnouncementApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.goodFinger.GoodFingerAnnouncementApplication.controller.AnnouncementController;

@SpringBootApplication
public class GoodFingerAnnouncementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodFingerAnnouncementApplication.class, args);
	}

}
