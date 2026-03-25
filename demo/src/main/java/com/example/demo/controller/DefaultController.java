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

@Controller
public class DefaultController {
	
	@Autowired // 서버가 실행될 때 선언된 객체를 하나 만듦(auto) ->모든 사용자가 하나의 객체를 돌려쓰게 함.
	DefaultService defaultService; //객체 선언
	
	@RequestMapping("/default.do") // 주소 
	public String test(Model model) throws Exception{
		return "/default"; // 파일명
	}
	
	@RequestMapping("/test.do") // 주소 
	public String test2(Model model) throws Exception{
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
		
		defaultService.getUserList();
		
		System.out.println("test.dox 호출됨");
		System.out.println(map);
		
		resultMap.put("result", "success");
		resultMap.put("Hello", "Word");
		
		return new Gson().toJson(resultMap); 
	}
	
}
