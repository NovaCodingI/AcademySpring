package com.ikik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ikik.service.ReplyService;
import com.ikik.vo.Criteria;
import com.ikik.vo.PageDto;
import com.ikik.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

/**
 * ●RestController
 * 		Controller가 REST 방식을 처리하기 위한 것임을 명시합니다.
 * @author user
 *
 */
@RestController
@Log4j
public class ReplyController extends CommonRestController {
	
	@Autowired
	ReplyService service;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	
	/**
	 * ●PathVariable
	 * 		URL 경로에 있는 값을 파라메터로 추출하려고 할 때 사용
	 * 		경로의 일부분을 파라메터로 사용
	 * 
	 *	/reply/list/83
	 * @return
	 */
	@GetMapping("/reply/list/{bno}/{page}")
//	public List<ReplyVO> getList(@PathVariable("bno") int bno, @PathVariable("page") int page){
	public Map<String, Object> getList(@PathVariable("bno") int bno, @PathVariable("page") int page){
		
		
		log.info("bno : " + bno);
		log.info("page : " + page);
		
		Criteria cri = new Criteria();
		cri.setPageNo(page);
		
		// 페이지 처리(시작번호~끝번호)
		List<ReplyVO> list = service.getList(bno, cri);
		int totalCnt = service.totalCnt(bno);
		
		// 페이지블럭을 생성
		PageDto pageDto = new PageDto(cri, totalCnt);
		
		/* responseListMap(list, pageDto) 로 처리
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageDto", pageDto);
		return map;
		*/
		return responseListMap(list, pageDto);
//		return service.getList(bno, cri);
	}
	
	@GetMapping("/reply/delete/{rno}")
	public Map<String, Object> delete(@PathVariable("rno") int rno){
		// 맵을 만들고 결과값을 반환하는 메서드를 생성해서 작업
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		int res = service.delete(rno);
//		if(res>0) {
//			map.put("result", "success");
//		} else {
//			map.put("result", "fail");
//			map.put("message", "댓글 삭제중 예외사항이 발생 하였습니다.");
//		}
//		return map;
		return responseDeleteMap(service.delete(rno));
	}
	
	/**
	 * ●RequestBody
	 * 		JSON 데이터를 원하는 타입으로 바인딩 처리
	 * @param vo
	 * @return
	 */
	@PostMapping("/reply/insert")
	public Map<String, Object> insert(@RequestBody ReplyVO vo){
		
		// 맵을 생성하고 결과를 처리하고
		Map<String, Object> map = new HashMap<String, Object>();
		
		log.info("=============================insert");
		log.info("replyVO" + vo);
		log.info("=============================insert");
		
		try {
			int res = service.insert(vo);
			
			return map = responseWriteMap(res);
			/*
			if(res>0) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
				map.put("message", "댓글 등록중 예외사항이 발생 하였습니다.");
			}
			*/
		} catch (Exception e) {
			
			map.put("result", "fail");
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	
	@PostMapping("/reply/editAction")
	public Map<String, Object> update(@RequestBody ReplyVO vo){
		/* 공통모듈을 사용하므로써 코드가 간결해졌어요
		Map<String, Object> map = new HashMap<String, Object>();
		int res = service.update(vo);
		if(res>0) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
			map.put("message", "댓글 수정중 예외사항이 발생 하였습니다.");
		}
		return map;
		*/
		return responseEditMap(service.update(vo));
	}
	
}














