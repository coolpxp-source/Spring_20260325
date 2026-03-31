package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.SchoolMapper;
import com.example.demo.model.Dept;
import com.example.demo.model.Professor;
import com.example.demo.model.Stu;

@Service
public class SchoolService {
	@Autowired
	SchoolMapper schoolMapper;
	
	public HashMap<String, Object> getProfList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			List<Professor> list = schoolMapper.selectProfList(map);
			List<Dept> deptList = schoolMapper.selectDeptList(map);
			resultMap.put("list", list);
			resultMap.put("deptList", deptList);
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_SEARCH);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> getStuList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			List<Stu> list = schoolMapper.selectStuList(map);
			List<Dept> deptList = schoolMapper.selectDeptList(map);
			resultMap.put("list", list);
			resultMap.put("deptList", deptList);
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_SEARCH);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> getDeptList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			List<Dept> list = schoolMapper.selectDeptList(map);
			resultMap.put("list", list);
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_SEARCH);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> addStu(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			 int result = schoolMapper.insertStu(map);
			
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_ADD);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> addProf(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			 int result = schoolMapper.insertProf(map);
			
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_ADD);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
}
