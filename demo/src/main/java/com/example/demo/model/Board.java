package com.example.demo.model;

import lombok.Data;

@Data
public class Board {
	int boardNo;
	String userId;
	String title;
	int cnt;
	int kind;
	String contents;
	String cdateTime;
	
	// 첨부파일
	int fileNo;
	String filePath;
	String fileName;
	String fileOrgName;
	String fileSize;
	String fileETC;
}
