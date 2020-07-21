package com.goodFinger.GoodFingerAnnouncementApplication.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public List<Announcement> getAnnouncementList() {
		
		return announcementRepo.findAll();
	}
	
	@Override
	public List<Announcement> getWaitingAnnouncementList() throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		String todayDate = format.format(time);
		
		return announcementRepo.findAllByStartDateAfter(todayDate);
	}
	
	@Override
	public String insertAnnouncement(Announcement announcement) throws Exception {
		mongoTemplate.insert(announcement);
		
		return "TRUE";
	}

	@Override
	public String insertTestData() throws Exception {
//		Announcement announcement = new Announcement();
//		announcement.setAnnouncementId("testId");
//		announcement.setApplicant(new String[] {"user1", "user2", "user3"});
//		announcement.setApplicant_questions(null);
//		announcement.setCategory(1);
//		announcement.setFlag("Y");
//		announcement.setCompany("goodfingerCom");
//		announcement.setLocationCity("seoul");
//		announcement.setLocationDistrict("dongjack");
//		announcement.setRecruitment(5);
//		announcement.setPreferredSex("all");
//		announcement.setPreferredAge(new int[] {10, 20 ,30});
//		announcement.setTask("매장관리");
//		announcement.setStartDate("2019-11-20");
//		announcement.setEndDate("2019-12-20");
//		announcement.setStartTime("00:00");
//		announcement.setEndTime("24:00");
//		announcement.setSalary(new String[] {"day", "time"});
//		announcement.setEtc("{당일지급:\"ok\", 무급휴게시간:\"ok\", 일용근로자신고업체:\"no\"}");
//		announcement.setJoboffer("{간단소개:\"솔루션회사입니다. 그룹웨어\", picture:[\"경로1\",\"경로2\",\"경로3\"]}");
//		announcement.setMemo("메모");
//		announcement.setQuestions("question array");
//		
//		mongoTemplate.insert(announcement);
		
		return "TRUE";
	}

}