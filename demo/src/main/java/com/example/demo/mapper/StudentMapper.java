package com.example.demo.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Student;

@Mapper
public interface StudentMapper {
	public List<Student> selectStudentList(HashMap<String, Object> map); //여러개 return -> list
	
	public int deleteStudent(HashMap<String, Object> map);
	
	public Student selectStudent(HashMap<String, Object> map);
	
	public int insertStudent(HashMap<String, Object> map); //selecte를 제외한 나머지는 return type int
	
}
