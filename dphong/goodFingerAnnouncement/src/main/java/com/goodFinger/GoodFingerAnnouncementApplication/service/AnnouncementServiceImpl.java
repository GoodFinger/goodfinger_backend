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
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public String testFunc() {
		List<Announcement> announcement = announcementRepo.findAll();
		
		return announcement.toString();
	}

	@Override
	public String insertTestData() throws Exception {
		Announcement announcement = new Announcement();
		announcement.setAnnouncementId("testId");
		announcement.setApplicant(new String[] {"user1", "user2", "user3"});
		announcement.setApplicant_questions(null);
		announcement.setCategory(1);
		announcement.setFlag("Y");
		announcement.setCompany("goodfingerCom");
		announcement.setLocationCity("seoul");
		announcement.setLocationDistrict("dongjack");
		announcement.setRecruitment(5);
		announcement.setPreferredSex("all");
		announcement.setPreferredAge(new int[] {10, 20 ,30});
		announcement.setTask("�������");
		announcement.setStartDate("2019-11-20");
		announcement.setEndDate("2019-12-20");
		announcement.setStartTime("00:00");
		announcement.setEndTime("24:00");
		announcement.setSalary(new String[] {"day", "time"});
		announcement.setEtc("{��������:\"ok\", �����ްԽð�:\"ok\", �Ͽ�ٷ��ڽŰ��ü:\"no\"}");
		announcement.setJoboffer("{���ܼҰ�:\"�ַ��ȸ���Դϴ�. �׷����\", picture:[\"���1\",\"���2\",\"���3\"]}");
		announcement.setMemo("�޸�");
		announcement.setQuestions("question array");
		
		mongoTemplate.insert(announcement);
		
		return "TRUE";
	}
	
}