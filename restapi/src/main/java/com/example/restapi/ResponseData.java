package com.example.restapi;

import org.springframework.stereotype.Component;

@Component
public class ResponseData {
	
	private String fileName;
	
	private String downloadURL;
	
	private String fileType;
	
	private Long fileSize;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public ResponseData()
	{
		
	}
	public ResponseData(String fileName, String downloadURL, String fileType, long l) {
		super();
		this.fileName = fileName;
		this.downloadURL = downloadURL;
		this.fileType = fileType;
		this.fileSize = l;
	}

	@Override
	public String toString() {
		return "ResponseData [fileName=" + fileName + ", downloadURL=" + downloadURL + ", fileType=" + fileType
				+ ", fileSize=" + fileSize + "]";
	}
}
