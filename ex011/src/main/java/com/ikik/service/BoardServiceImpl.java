package com.ikik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ikik.mapper.BoardMapper;
import com.ikik.mapper.ReplyMapper;
import com.ikik.vo.BoardVO;
import com.ikik.vo.Criteria;
import com.ikik.vo.FileuploadVO;
import com.ikik.vo.PageDto;

/**
 * 
 * 각 계층간의 연결은 인터페이스를 활용하여 느슨한 결합을 합니다.
 * 느슨한 결합 : 하나의 컴포넌트의 변경이 다른 컴포넌트들의 변경을 요구하는 위험을 줄이는 것을
 * 			목적으로 하는 시스템에서 컴포넌트 간의 내부 의존성을 줄이는 것을 추구하는 디자인 목표
 * 
 * ● Service
 * 		계층 구조상 비지니스 영역을 담당하는 객체임을 표시
 * 
 * ● root-context.xml
 * 		component-scan 속성에 패키지를 등록 합니다.
 * 
 * ● 서비스를 Interface로 생성하는 이유
 * 	1. 내부로직의 분리
 * 		인터페이스를 사용함으로써 내부로직의 변경, 수정시 유연하게 대체할 수 있다
 * 	2. 구현체의 전환이 용이
 * 		구현체의 변경, 교체가 용이 합니다.
 * 	3. 테스트 용이성
 * 		단위 테스트 시 테스트용 구현체를 이용함으로써 테스트를 수행
 * 
 * @author user
 *
 */
// 인터페이스가 변경이 되면 서로간의 (상호간의) 문제가!! 인터페이스(설계)를 구현하는 부분이 중요합니다
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	
	@Override
	public List<BoardVO> getListXml(Criteria cri, Model model) {
		/*
		 * 1. 리스트 조회
		 * 		- 검색어, 페이지정보(startNo ~ endNo까지 조회)
		 * 2. 총건수 조회
		 * 3. pageDto객체 생성
		 */
		List<BoardVO> list = boardMapper.getListXml(cri);
		int totalCnt = boardMapper.getTotalCnt(cri);
		PageDto pageDto = new PageDto(cri, totalCnt);
		
		model.addAttribute("list", list);
		model.addAttribute("totalCnt");
		model.addAttribute("pageDto", pageDto);
		
		
		
		
		
		
		
		// 서비스가 다 처리해서 반환할 필요가 없음
		return null;
//		return boardMapper.getListXml(cri);
	}

	@Override
	public int insert(BoardVO board) {
		
		return boardMapper.insert(board);
	}

	/* 파일첨부도 추가해서 주석처리
	@Override
	public int insertSelectKey(BoardVO board) {
		
		return boardMapper.insertSelectKey(board);
	}
	*/

	@Override
	public BoardVO getOne(int bno) {
		
		return boardMapper.getOne(bno);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(int bno) {
		// 게시물을 삭제시 첨부된 파일이 있는 경우 오류가 발생
		// → 게시물 삭제시 댓글 및 첨부파일이 모두 삭제 된다는 알림을 띄우고
		// 확인 버튼을 클릭하면 모두 삭제 처리
		
		// 1. 첨부파일을 모두 삭제
		// 첨부파일 리스트 조회 - fileuploadService
		List<FileuploadVO> list = fileuploadService.getList(bno);
		int res = 0;
		for(FileuploadVO vo : list) {
			// 리스트를 반복을 통해서 돌면서 삭제 처리 - List는 없지만 삭제처리가 있음
			// 삭제처리 - fileuploadService
			res += fileuploadService.delete(bno, vo.getUuid());
		}
		// 2. 댓글 삭제
		replyMapper.deleteReplyList(bno);
		
		// 3. 게시글 삭제
		return boardMapper.delete(bno);
	}

//	@Override
//	public int update(BoardVO board) {
//		
//		return boardMapper.update(board);
//	}

	@Override
	public int getTotalCnt(Criteria cri) {

		return boardMapper.getTotalCnt(cri);
	}

	@Override
	public int updateReplyCnt(int bno, int amount) {
		return boardMapper.updateReplyCnt(bno, amount);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertSelectKey(BoardVO board, List<MultipartFile> files) throws Exception {

		// 게시물 등록
		int res = boardMapper.insertSelectKey(board);
		
		// 파일 첨부
		fileuploadService.fileupload(files, board.getBno());
		
		
		return res;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(BoardVO board, List<MultipartFile> files) throws Exception {
		// 게시물 수정
		int res = boardMapper.update(board);
				
		// 파일 첨부
		fileuploadService.fileupload(files, board.getBno());
		return res;
	}
}
