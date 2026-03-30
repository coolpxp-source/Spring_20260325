package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.User;

@Mapper
public interface UserMapper { //인터페이스
	// 사용자 목록
	public List<User> selectUserList(HashMap<String, Object> map);
	
	public User selectUser(HashMap<String, Object>map); 
	
	public int insertUser(HashMap<String, Object>map); // select 제외하고는 return 타입 전부 int(영향을 줬는지 아닌지만 보기 때문에) 
	
	// 삭제
	public int deleteUser(HashMap<String, Object> map);
}
