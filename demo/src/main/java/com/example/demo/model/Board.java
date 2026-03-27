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
}
