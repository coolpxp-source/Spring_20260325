package com.example.demo.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.User;

@Mapper
public interface UserMapper { //인터페이스
	public User selectUser(HashMap<String, Object>map); 
	
	public int insertUser(HashMap<String, Object>map); // select 제외하고는 return 타입 전부 int(영향을 줬는지 아닌지만 보기 때문에) 
}
