package com.ikik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikik.service.BoardService;
import com.ikik.vo.BoardVO;
import com.ikik.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	/**
	 *  /board/msg
	 *  WEB-INF/views/board/msg.jsp
	 * @param model
	 */
	@GetMapping("msg")
	public void msg(Model model) {
		
	}
	
	// 부트스트랩 써보자
	@GetMapping("message")
	public void message(Model model) {
		
	}
	
	// 부트스트랩 써보자
	@GetMapping("message1")
	public void message1(Model model) {
		
	}
	
	@GetMapping("list_bs")
	public void list_bs(Model model) {
		
	}
	
	@Autowired
	BoardService boardService;
	
	/**
	 * 기본생성자가 있어야합니다.
	 * 파라메터의 자동수집
	 * 기본생성자를 이용해서 객체를 생성, 넘어온 데이터들을
	 * -> setter 메서드를 이용해서 세팅
	 * @param model
	 * @param cri
	 */
//	@GetMapping("/board/list") //RequestMapping을 이용하여..
	@GetMapping("list")
//	public void getList(Model model) {
	public void getList(Model model, Criteria cri) {
		boardService.getListXml(cri, model);
		// 리스트를 더이상 반환하지 않아도 됩니다.
//		List<BoardVO> list = boardService.getListXml();
//		List<BoardVO> list = boardService.getListXml(cri, model);
		log.info("========================");
		log.info("cri : " + cri);
//		log.info("list : " + list);
		log.info("========================");
//		model.addAttribute("list", boardService.getListXml());
//		model.addAttribute("list", list);
		
	}
	
	@GetMapping("view")
	public void getOne(Model model, BoardVO paramVO) {
		
		log.info("================== bno" + paramVO);
		BoardVO board = boardService.getOne(paramVO.getBno());
		// board라는 이름에다가 board 객체를 저장했습니다.
		model.addAttribute("board", board);
//		model.addAttribute("board", boardService.getOne(paramVO.getBno()));
		
	}
	
	/**
	 * requestMapping에 /board/ 가 설정 되어 있으므로
	 *	/board/write
	 * @param model
	 */
	@GetMapping("write")
	public void write(Model model) {
		
		// return "/board/write"; // 요청하는 그대로 반환하는것 void, board에 있는 write 요청에 있는 board
		// return "write"; // WEB-INF/views/ + return + .jsp
	}
	
	
	
	/**
	 * ●RedirectAttributes
	 * 
	 * 	리다이렉트 URL의 화면까지 데이터 전달
	 * 
	 * 	Model과 같이 매개변수로 받아 사용
	 * 	addFlashAttribute : 세션에 저장 후 페이지 전환 ( 세션에 잠깐 담았다가 지웁니다. )
	 * 
	 * 	addAttribute는 주소표시줄에 넘어갑니다.   ?msg=등록되었습니다.
	 * 
	 * @param board
	 * @param rttr
	 * @param model
	 * @return
	 */
	@PostMapping("write")
	// 기본타입은 메모리 공간안에 바로 저장
	// 페이지가 아니라 어떤 처리를 할꺼야 하면 같은 이름보다는 Action과 같이 뒤에 표시를 해주면 좋아요
	public String writeAction(BoardVO board
								, RedirectAttributes rttr
								, Model model) {
//		req.setCharacterEncoding("utf-8"); // web.xml에 필터가 알아서 처리합니다. // (HttpServletRequest req) 매개변수
		log.info("================== board" + board);
		
//		int res = boardService.insert(board);
		// 레퍼런스의 타입이기때문에 변경사항이 있으면 유지가 되는것 (주소값으로 판단)
		// 시퀀스 조회 후 시퀀스 번호를 bno에 저장
		int res = boardService.insertSelectKey(board);

		String msg = "";
		// 실패 Test를 하고싶다면 부등호만 바꿔서 한번 해봅시다
//		if(res<0) {
		if(res>0) {
			msg = board.getBno() + "번 등록되었습니다";
//			msg = "등록되었습니다";
			// url?msg=등록 (쿼리스트링으로 전달 -> param.msg)
//			rttr.addAttribute("msg", msg);
			
			// 세션영역에 저장 -> msg
			// 새로고침시 유지되지 않음
			rttr.addFlashAttribute("msg", msg); // 근데 알람이 안떠요.. param.msg로 부르지말고 msg로 부르면 돼요
			return "redirect:/board/list";
		} else {
			msg = "등록중 예외사항이 발생 하였습니다.";
			model.addAttribute("msg", msg);
			return "/board/message";
		}
//		model.addAttribute("board", boardService.insert(board));
//		model.addAttribute("msg", msg);
	    // return "/board/write"; // 요청하는 그대로 반환하는것 void, board에 있는 write 요청에 있는 board
	    // return "write"; // WEB-INF/views/ + return + .jsp
	}
	
	/**
	 * 수정하기
	 * 	- bno를 파라메터로 받아야 함
	 * 	- 버튼, 버튼의 액션이 달라짐
	 */
	@GetMapping("edit")
	public String edit(BoardVO paramVO, Model model) {
		// 게시물 전부 조회했어요
		BoardVO board = boardService.getOne(paramVO.getBno());
		model.addAttribute("board", board);
		
		// 경로반환 : 글쓰기 페이지
		return "/board/write";
		
		/* 혼자 해본거
		log.info("================== board" + board);
		
//		int res = boardService.insert(board);
		// 레퍼런스의 타입이기때문에 변경사항이 있으면 유지가 되는것 (주소값으로 판단)
		// 시퀀스 조회 후 시퀀스 번호를 bno에 저장
		int res = boardService.update(board);
		System.out.println("=======res : " + res);
		String msg = "";
		
		if(res>0) {
			msg = board.getBno() + "번 수정완료 되었습니다";
//			msg = "등록되었습니다";
			// url?msg=등록 (쿼리스트링으로 전달 -> param.msg)
//			rttr.addAttribute("msg", msg);
			
			// 세션영역에 저장 -> msg
			// 새로고침시 유지되지 않음
			rttr.addFlashAttribute("msg", msg); // 근데 알람이 안떠요.. param.msg로 부르지말고 msg로 부르면 돼요
			return "redirect:/board/view";
		} else {
			msg = "등록중 예외사항이 발생 하였습니다.";
			model.addAttribute("msg", msg);
			return "/board/message";
		}
		*/
	}
	
	@PostMapping("editAction")
	public String editAction(BoardVO board
							, RedirectAttributes rttr
							, Model model) {

		// 수정
		int res = boardService.update(board);
		
		if(res>0) {
			// addAttribute(파라메터 영역에 저장됩니다)
//			model.addAttribute("msg", "수정 되었습니다.");
			
			// 세션영역에 저장
			rttr.addFlashAttribute("msg", "수정되었습니다.");
			// 상세페이지로 이동
			// 다른 url를 호출하고싶은거에요 redirect해줘야죠 근데 메세지가 안떠 리퀘스트영역이 공유가 안되어있어서
			// RedirectAttributes rttr 객체를 이용해서 사용합니다.
			// Redirect 할때는 model에다 넣어도 소용이 없습니다. (request 영역이 공유 되지 않으므로)
			return "redirect:/board/view?bno=" + board.getBno();
		} else {
			model.addAttribute("msg", "수정중 예외사항이 발생 하였습니다.");
//			rttr.addFlashAttribute("msg", "수정중 예외사항이 발생 하였습니다.");
			return "/board/message";
		}
		
	}
	
	// 혼자 해보다가 망한것
	@PostMapping("edit")
	public String edit(BoardVO paramVO
						, RedirectAttributes rttr
						, Model model) {
		BoardVO board = boardService.getOne(paramVO.getBno());
		model.addAttribute("board", paramVO);
		log.info("================== board" + paramVO);
		
//		int res = boardService.insert(board);
		// 레퍼런스의 타입이기때문에 변경사항이 있으면 유지가 되는것 (주소값으로 판단)
		// 시퀀스 조회 후 시퀀스 번호를 bno에 저장
		int res = boardService.update(board);
		System.out.println("=======res : " + res);
		String msg = "";
		
		if(res>0) {
			msg = board.getBno() + "번 수정완료 되었습니다";
//			msg = "등록되었습니다";
			// url?msg=등록 (쿼리스트링으로 전달 -> param.msg)
//			rttr.addAttribute("msg", msg);
			
			// 세션영역에 저장 -> msg
			// 새로고침시 유지되지 않음
			rttr.addFlashAttribute("msg", msg); // 근데 알람이 안떠요.. param.msg로 부르지말고 msg로 부르면 돼요
			return "redirect:/board/view";
		} else {
			msg = "등록중 예외사항이 발생 하였습니다.";
			model.addAttribute("msg", msg);
			return "/board/message";
		}
	}
	// 혼자 해보다가 망한것
	
	@GetMapping("delete")
	public String delete(BoardVO board
						, RedirectAttributes rttr
						, Model model) {
		
		int res = boardService.delete(board.getBno());
		System.out.println("================res" + res);
		if(res>0) {
			
			rttr.addFlashAttribute("msg", "삭제되었습니다.");
			return "redirect:/board/list";
		} else {
			model.addAttribute("msg", "삭제중 예외가 발생 하였습니다.");
			return "/board/message";
		}
	}
}
