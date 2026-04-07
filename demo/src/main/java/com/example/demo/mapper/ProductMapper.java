package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Product;

@Mapper // xml에서 implement가 가능해진다. 
public interface ProductMapper{
	// 여러개 리턴 -> selectXXXList
	public List<Product> selectOrderList(HashMap<String, Object> map);
	
}