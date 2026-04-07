package com.example.demo.model;

import lombok.Data;

@Data // getter, setter method
public class User {
	String userId; //컬럼명이랑 같기만 하면 됨(대소문자 구분x)
	String userName;
	String pwd;
	String gender;
	String role;
	
	// 첨부파일
	int fileNo;
	String filePath;
	String fileName;
	String fileOrgName;
	String fileSize;
	String fileETC;
}
