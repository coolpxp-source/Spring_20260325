package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.SchoolService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@RequestMapping("/stu/add.do") // 주소 
	public String add(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/School/stu-add"; // 파일명
	}
	
	@RequestMapping("/prof/add.do") // 주소 
	public String add2(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/School/prof-add"; // 파일명
	}
	
	@RequestMapping("/stu/view.do") // 주소 
	public String veiw(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);
		request.setAttribute("map", map);
		return "/School/stu-view"; // 파일명
	}
	
	@RequestMapping("/prof/view.do") // 주소 
	public String veiw2(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);
		request.setAttribute("map", map);
		return "/School/prof-view"; // 파일명
	}
	
	@RequestMapping("/stu/edit.do") // 주소 
	public String edit(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);
		request.setAttribute("map", map);
		return "/School/stu-edit"; // 파일명
	}
	
	@RequestMapping("/prof/edit.do") // 주소 
	public String edit2(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);
		request.setAttribute("map", map);
		return "/School/prof-edit"; // 파일명
	}
		
	@RequestMapping(value = "/prof/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String list1(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int pageSize = Integer.parseInt((String)map.get("pageSize"));
		int offSet = Integer.parseInt((String)map.get("offSet"));
		map.put("pageSize", pageSize);
		map.put("offSet", offSet);
		
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
	
	// ajax가 호출하는 주소   
	@RequestMapping(value = "/dept/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String list3(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap = schoolService.getDeptList(map);
		return new Gson().toJson(resultMap); 
	}
	
	// ajax가 호출하는 주소   
	@RequestMapping(value = "/stu/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.addStu(map);
		return new Gson().toJson(resultMap); 
	}
	
	// ajax가 호출하는 주소   
	@RequestMapping(value = "/prof/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String add2(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.addProf(map);
		return new Gson().toJson(resultMap); 
	}
	
	// ajax가 호출하는 주소   
	@RequestMapping(value = "/stu/check.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String check(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.getStu(map);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/stu/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String remove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.removeStu(map);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/stu-remove-all.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String removeAll(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String json = map.get("selectList").toString(); 
		ObjectMapper mapper = new ObjectMapper();
		List<Object> list = mapper.readValue(json, new TypeReference<List<Object>>(){});
		map.put("list", list);
		System.out.println(map);
		resultMap = schoolService.removeAllStu(map);
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/prof/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String remove2(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.removeProf(map);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/stu/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String info(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.getStuInfo(map);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/prof/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String info2(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.getProfInfo(map);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/stu/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String edit(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.editStu(map);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/prof/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String edit2(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = schoolService.editProf(map);
		return new Gson().toJson(resultMap); 
	}
	
}
