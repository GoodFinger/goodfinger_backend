package com.goodFinger.GoodFingerAnnouncementApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.goodFinger.GoodFingerAnnouncementApplication.Repo.AnnouncementRepo;
import com.goodFinger.GoodFingerAnnouncementApplication.document.Announcement;

@Component("AnnouncementService")
public class AnnouncementServiceImpl implements AnnouncementService {
	
	@Autowired
	private AnnouncementRepo announcementRepo;
	
	private MongoTemplate mongoTemplate;

	public String testFunc() {
		List<Announcement> announcement = announcementRepo.findAll();
		
		System.out.println(announcement.get(0).getValue());
		
		return announcement.toString();
	}
	
}