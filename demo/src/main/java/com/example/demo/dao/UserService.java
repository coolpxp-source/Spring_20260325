package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public HashMap<String, Object> login(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		User user = userMapper.selectUser(map);
		if(user != null) {
//			ooo님 환영합니다.
			if(user.getPwd().equals(map.get("pwd"))) {
				resultMap.put("message", user.getUserName() + "님 환영합니다.");
			}else {
				resultMap.put("message", "비밀번호를 확인해주세요.");
			}
			resultMap.put("message", "로그인 성공!");
		}else {
			resultMap.put("message", "존재하지 않는 아이디 입니다.");
		}
		resultMap.put("result", "success");
		
		return resultMap;
	}
	
	public HashMap<String, Object> addUser(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 예외처리 권장
		try { 
			int cnt = userMapper.insertUser(map);
			if(cnt > 0) {
				resultMap.put("message", "회원가입을 축하합니다.");
			}else {
				resultMap.put("message", "가입에 실패했습니다. 다시 시도해주세요.");
			}
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message","서버 에러 발생 \n잠시 후 다시 시도하세요."); // \n은 다음줄로 이동
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 아이디 중복체크 service
	public HashMap<String, Object> checkUser(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			User user = userMapper.selectUser(map);
			if(user != null) {
				resultMap.put("message", "이미 사용 중인 아이디 입니다.");
				resultMap.put("result", false);
			}else {
				resultMap.put("message", "사용 가능한 아이디 입니다.");
				resultMap.put("result", true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러 발생");
		}
		resultMap.put("result", "success");
		
		return resultMap;
	}

}
