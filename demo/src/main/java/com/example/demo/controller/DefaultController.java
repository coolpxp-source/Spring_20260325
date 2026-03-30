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
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
	
	@Autowired // 서버가 실행될 때 선언된 객체를 하나 만듦(auto) ->모든 사용자가 하나의 객체를 돌려쓰게 함.
	DefaultService defaultService; //객체 선언
	
	// 웹브라우저로 접속하는 주소, return은 jsp파일
	@RequestMapping("/copy.do") // 주소 
	public String copy(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/copy"; // 파일명
	}
	
	// ajax가 호출하는 주소
	@RequestMapping(value = "/copy.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String copy(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// resultMap = 서비스객체.함수(map);
		return new Gson().toJson(resultMap); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// =========================================
	@RequestMapping("/default.do") // 주소 
	public String test(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/default"; // 파일명
	}
	
	@RequestMapping("/test.do") // 주소 
	public String test2(HttpServletRequest request, Model model) throws Exception{
		return "/test"; // 파일명
	}
	
	
	@RequestMapping(value = "/default.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/test.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String test(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = defaultService.getUserList();
		
		return new Gson().toJson(resultMap); 
	}
	
	
	
}
