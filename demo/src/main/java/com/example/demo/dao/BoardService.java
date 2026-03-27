package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.Board;

@Service
public class BoardService {
	@Autowired
	BoardMapper boardMapper;

	public HashMap<String, Object> getBoardList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Board> list = boardMapper.selectBoardList(map);
			resultMap.put("list", list); // key, value -> value에 "list"를 넣으면 list의 data가 아니라 문자열이 넘어감.
			resultMap.put("message", "데이터 조회 성공"); 
			resultMap.put("result","success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "데이터 조회 실패"); 
			resultMap.put("result","fail");
		}
		return resultMap;
	}
	
	public HashMap<String, Object> addBoard(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boardMapper.insertBoard(map);
			resultMap.put("message", "등록되었습니다."); 
			resultMap.put("result","success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("message", "등록 실패"); 
			resultMap.put("result","fail");
		}
		return resultMap;
	}
	
	public HashMap<String, Object> getBoard(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(map.get("kind").equals("view")) {
				boardMapper.updateCnt(map); // 조회수 업데이트 +1 
			}
			Board info = boardMapper.selectBoard(map); // 게시글 상세조회(view)
			resultMap.put("info", info); // key, value -> value에 "list"를 넣으면 list의 data가 아니라 문자열이 넘어감.
			resultMap.put("message", "데이터 조회 성공"); 
			resultMap.put("result","success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "데이터 조회 실패"); 
			resultMap.put("result","fail");
		}
		return resultMap;
	}
	
	public HashMap<String, Object> editBoard(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boardMapper.updateBoard(map);
			resultMap.put("message", "수정되었습니다."); 
			resultMap.put("result","success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("message", "수정 실패"); 
			resultMap.put("result","fail");
		}
		return resultMap;
	}

}
