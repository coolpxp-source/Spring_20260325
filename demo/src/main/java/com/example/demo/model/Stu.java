package com.example.demo.model;

import lombok.Data;

@Data
public class Stu {
	String stuNo;
	String name;
	String id;
	int grade;
	String jumin;
	String birthday;
	String tel;
	String deptNo1;
	String deptNo2;
	String profNo;
	
	// 대학~학과 join department table 
	String dName1; // 학부
	String dName2; // 학과1
	String dName3; // 부전공
	
	// 담당 교수 이름 join professor table
	String pName;
	
	
}
