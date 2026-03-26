package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Student;
import com.example.demo.model.User;

@Mapper // xml에서 implement가 가능해진다. 
public interface DefaultMapper{
	public List<User> selectUserList();

}