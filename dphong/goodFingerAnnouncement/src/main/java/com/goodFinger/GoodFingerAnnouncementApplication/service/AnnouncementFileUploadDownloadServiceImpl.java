package com.goodFinger.GoodFingerAnnouncementApplication.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.goodFinger.GoodFingerAnnouncementApplication.exception.FileDownloadException;
import com.goodFinger.GoodFingerAnnouncementApplication.exception.FileUploadException;
import com.goodFinger.GoodFingerAnnouncementApplication.property.FileUploadProperties;

@Service("fileService")
public class AnnouncementFileUploadDownloadServiceImpl implements AnnouncementFileUploadDownloadService {
	private final Path fileLocation;
	
	@Autowired
	public AnnouncementFileUploadDownloadServiceImpl(FileUploadProperties prop) {
		this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileLocation);
		} catch (Exception e) {
			throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
		}
	}
	
	@Override
	public String storeFile(MultipartFile file) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if (fileName.contains("..")) {
				throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
			}
			
			Path targetlocation = this.fileLocation.resolve(fileName);
			
			Files.copy(file.getInputStream(), targetlocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		} catch (Exception e) {
			throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
			}
			
		} catch (MalformedURLException e) {
			throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
		}
	}
		
}