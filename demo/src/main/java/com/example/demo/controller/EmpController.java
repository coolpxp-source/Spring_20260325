package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.dao.EmpService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	
	// 웹브라우저로 접속하는 주소, return은 jsp파일
		@RequestMapping("/empList.do") // 주소 
		public String list(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
			return "/emp/emp-list"; // 파일명
		}
		
		@RequestMapping("/emp/add.do") // 주소 
		public String add(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
			return "/emp/emp-add"; // 파일명
		}
		
		@RequestMapping("/emp/view.do") // 주소 
		public String view(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
			System.out.println(map);
			model.addAttribute("map", map);
			return "/emp/emp-view"; // 파일명
		}
		
		@RequestMapping("/emp/edit.do") // 주소 
		public String edit(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
			System.out.println(map);
			model.addAttribute("map", map);
			return "/emp/emp-edit"; // 파일명
		}
		
		// ajax가 호출하는 주소
		@RequestMapping(value = "/empList.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody // ajax->json 형태로 받을 때 
		public String list(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			int pageSize = Integer.parseInt((String)map.get("pageSize"));
			int offSet = Integer.parseInt((String)map.get("offSet"));
			map.put("pageSize", pageSize);
			map.put("offSet", offSet);
			
			resultMap = empService.getEmpList(map);
			return new Gson().toJson(resultMap); 
		}
		
		// ajax가 호출하는 주소
		@RequestMapping(value = "/empAdd.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody // ajax->json 형태로 받을 때 
		public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			 resultMap = empService.addEmp(map);
			return new Gson().toJson(resultMap); 
		}
		
		// ajax가 호출하는 주소
		@RequestMapping(value = "/empInfo.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody // ajax->json 형태로 받을 때 
		public String view(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			 resultMap = empService.getEmp(map);
			return new Gson().toJson(resultMap); 
		}
		
		// ajax가 호출하는 주소
		@RequestMapping(value = "/empRemove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody // ajax->json 형태로 받을 때 
		public String remove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap = empService.RemoveEmp(map);
			return new Gson().toJson(resultMap); 
		}
}
