package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MapController {
	
	@RequestMapping("/map1.do") // 주소 
	public String map(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/map/map1"; // 파일명
	}
	
	@RequestMapping("/map2.do") // 주소 
	public String map2(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/map/map2"; // 파일명
	}

}
