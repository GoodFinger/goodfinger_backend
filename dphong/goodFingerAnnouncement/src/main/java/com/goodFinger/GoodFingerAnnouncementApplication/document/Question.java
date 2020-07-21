package com.goodFinger.GoodFingerAnnouncementApplication.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javassist.SerialVersionUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class Question implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String questionId;
	private String contactUserId;
	private String contactName;
	private String contactDate;
	private String contactData;
	private String contactComment;

}