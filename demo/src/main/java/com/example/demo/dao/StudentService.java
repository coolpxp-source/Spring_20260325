package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.model.User;

@Service
public class StudentService {
	@Autowired
	StudentMapper studentMapper;

	public HashMap<String, Object> getStudentList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Student> list = studentMapper.selectStudentList(map);
		
		resultMap.put("list", list); // key, value -> value에 "list"를 넣으면 list의 data가 아니라 문자열이 넘어감.
		resultMap.put("message", "데이터 조회 성공");
		resultMap.put("result","success");
		return resultMap;
	}
	
	// delete
	public HashMap<String, Object> removeStudent(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int cnt = studentMapper.deleteStudent(map);
		if(cnt > 0) {
			resultMap.put("message", "삭제 성공");
		}else {
			resultMap.put("message", "삭제 실패");
		}
		resultMap.put("result","success");
		
		return resultMap;
	}
	
	//중복check
	public HashMap<String, Object> getStudent(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Student info = studentMapper.selectStudent(map);
		if(info != null) {
			resultMap.put("message", "이미 사용 중인 학번입니다.");
			resultMap.put("result","fail");
		}else {
			resultMap.put("message", "사용 가능한 학번입니다.");
			resultMap.put("result","success");
		}
		return resultMap;
	}
	
	// insert
		public HashMap<String, Object> addStudent(HashMap<String, Object> map){
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			try {
				int cnt = studentMapper.insertStudent(map);
				resultMap.put("message", "추가되었습니다.");
				resultMap.put("result","success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				resultMap.put("message", "서버 에러 발생.");
				resultMap.put("result","fail");
			}
			
			return resultMap;
		}
		
}

