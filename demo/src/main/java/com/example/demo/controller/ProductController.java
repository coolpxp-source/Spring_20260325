package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ProductService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	// 웹브라우저로 접속하는 주소, return은 jsp파일
	@RequestMapping("/product/chart.do") // 주소 
	public String chart(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/Product/chart"; // 파일명
	}
	
	@RequestMapping("/product/order.do") // 주소 
	public String order(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/Product/order-chart"; // 파일명
	}
	
	@RequestMapping("/product/payment.do") // 주소 
	public String pay(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/Product/payment"; // 파일명
	}
	
	@RequestMapping("/product/auth.do") // 주소 
	public String auth(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/Product/auth"; // 파일명
	}
	
	
	// ajax가 호출하는 주소
	@RequestMapping(value = "/product/order.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // ajax->json 형태로 받을 때 
	public String order(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap = productService.getOrderList(map);
		return new Gson().toJson(resultMap); 
	}

	
}
