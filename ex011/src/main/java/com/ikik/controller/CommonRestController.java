package com.ikik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.ikik.vo.PageDto;
import com.ikik.vo.ReplyVO;

// RestController로 받으면 왜 안될까요?
@Controller
public class CommonRestController {

	// 상수로 대입, 상수는 대문자로
	private final String REST_WRITE = "등록";
	private final String REST_EDIT = "수정";
	private final String REST_DELETE = "삭제";
	private final String REST_SELECT = "조회";
	
	// 상단에 상수가 정해져있고 메서드에 이름을
	// 사용자에게는 다른 이름을 
	/**
	 * 입력, 수정, 삭제의 경우 int 값을 반환합니다.
	 * 결과를 받아서 Map을 생성 후 반환 합니다.
	 * 클래스에 담아서 상속받아서 쓰면 페이지마다 작성안해줘도 됩니다.
	 * @return
	 */
	// map을 생성 후 result, msg 세팅
	public Map<String, Object> responseMap(int res, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(res>0) {
			map.put("result", "success");
			map.put("msg", msg + "되었습니다.");
		} else {
			map.put("result", "fail");
			map.put("msg", msg + "중 예외가 발생하였습니다.");
		}
		
		return map;
	}
	
	// 상수로 선언
	
	public Map<String, Object> responseWriteMap(int res) {
		
//		return responseMap(res, "등록");
		return responseMap(res, REST_WRITE);
	}
	
	public Map<String, Object> responseEditMap(int res) {
		
//		return responseMap(res, "수정");
		return responseMap(res, REST_EDIT);
	}
	
	public Map<String, Object> responseDeleteMap(int res) {
		
//		return responseMap(res, "삭제");
		return responseMap(res, REST_DELETE);
	}
//	public Map<String, Object> responseListMap(List<ReplyVO> list, PageDto pageDto) {
	// 어짜피 담아서 저장만 해주는것이기 떄문에 Type은 상관이 없고 ? 로 받아오면 모든 data를 받아옵니다. 
	public Map<String, Object> responseListMap(List<?> list, PageDto pageDto) {

		int res = list !=null ? 1 : 0;
		Map<String, Object> map = responseMap(res, REST_SELECT);
		map.put("list", list);
		map.put("pageDto", pageDto);
		return map;
	}

}