package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserService;
import com.example.demo.model.User;
import com.google.gson.Gson;

@Controller
public class UserController {
	
	@Autowired // 서버가 실행될 때 선언된 객체를 하나 만듦(auto) ->모든 사용자가 하나의 객체를 돌려쓰게 함.
	UserService userService; //객체 선언
	
	@RequestMapping("/login.do") // 주소 
	public String login(Model model) throws Exception{
		return "/User/User-login"; // 파일명
	}
	
	@RequestMapping("/join.do") // 주소 
	public String join(Model model) throws Exception{
		return "/User/User-sign-up"; // 파일명, jsp 생략가능
	}
	
	@RequestMapping(value = "/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map);
		resultMap  = userService.login(map);
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/join.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String addUser(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map);
		resultMap  = userService.addUser(map);
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/check.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String checkUser(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map);
		resultMap  = userService.checkUser(map);
		
		return new Gson().toJson(resultMap); 
	}
	
}
