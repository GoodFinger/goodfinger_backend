package com.goodFinger.GoodFingerAnnouncementApplication.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javassist.SerialVersionUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document(collection = "Announcement")
@Getter @Setter
public class Announcement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String announcementId;
	private String flag;
	private String company;
	private String[] category;
	private String locationCity;
	private String locationDistrict;
	private int recruitment; //모집인원
	private String preferredSex;
	private int[] preferredAge = {10, 20 ,30};
	private String task;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String[] salary = {"day", "time", "month"};
	private String etc;
	private String joboffer;
	private String[] picture;
	private String memo;
	private String name;
	private String questions;
	private String applicant_questions;
	private String[] applicant;

}