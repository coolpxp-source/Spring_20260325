package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.SchoolService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SchoolController {
	@Autowired
	SchoolService schoolService;
	@RequestMapping("/prof/list.do") // 주소 
	public String copy(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/School/prof-list"; // 파일명
	}

	@RequestMapping("/stu/list.do") // 주소 
	public String list(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/School/stu-list"; // 파일명
	}
		
	@RequestMapping(value = "/prof/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String list1(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap = schoolService.getProfList(map);
		return new Gson().toJson(resultMap); 
	}
	
	// ajax가 호출하는 주소   
	@RequestMapping(value = "/stu/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String list2(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap = schoolService.getStuList(map);
		return new Gson().toJson(resultMap); 
	}
		
	
}
