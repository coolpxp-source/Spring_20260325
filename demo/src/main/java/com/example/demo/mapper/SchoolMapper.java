package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Dept;
import com.example.demo.model.Professor;
import com.example.demo.model.Stu;

@Mapper
public interface SchoolMapper {
	// 교수 목록
	public List<Professor> selectProfList(HashMap<String, Object> map);
	// 학생 목록
	public List<Stu> selectStuList(HashMap<String, Object> map);
	// 학과 목록
	public List<Dept> selectDeptList(HashMap<String, Object> map);
	// 학생 추가
	public int insertStu(HashMap<String, Object> map);
	// 교수 추가
	public int insertProf(HashMap<String, Object> map);
}
