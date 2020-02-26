package com.goodFinger.GoodFingerAnnouncementApplication.Repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.goodFinger.GoodFingerAnnouncementApplication.document.Announcement;

public interface AnnouncementRepo extends MongoRepository<Announcement, Long> {
	public List<Announcement> findAll();
	public List<Announcement> findById(String id);
	public List<Announcement> findByName(String name);
}