package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.DefaultService;
import com.example.demo.dao.StudentService;
import com.google.gson.Gson;

@Controller
public class StudentController {
	
	@Autowired // 서버가 실행될 때 선언된 객체를 하나 만듦(auto) ->모든 사용자가 하나의 객체를 돌려쓰게 함.
	StudentService studentService; //객체 선언
	
	@RequestMapping("/stu-list.do") // 주소 
	public String test(Model model) throws Exception{
		return "/student/stu-list"; // 파일명
	}
	
	@RequestMapping(value = "/stu-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String test(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = studentService.getStudentList();
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/stu-remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String remove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map);
		resultMap = studentService.removeStudent(map);
		
		return new Gson().toJson(resultMap); 
	}
	
}
