package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.Emp;
import com.example.demo.model.User;

@Mapper
public interface EmpMapper {
	// 목록
	public List<Emp> selectEmpList(HashMap<String, Object> map);
	// 삽입
	public int insertEmp(HashMap<String, Object> map);
	// 한 개 리턴 -> selectXXX
	public Emp selectEmp(HashMap<String, Object> map);
	// 삭제
	public int deleteEmp(HashMap<String, Object> map);
	//갯수 카운트 
	public int selectEmpCount(HashMap<String, Object> map);
	
}
