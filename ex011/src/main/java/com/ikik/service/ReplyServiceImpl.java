package com.ikik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikik.mapper.BoardMapper;
import com.ikik.mapper.ReplyMapper;
import com.ikik.vo.Criteria;
import com.ikik.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper replyMapper;
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<ReplyVO> getList(int bno, Criteria cri) {
		// TODO Auto-generated method stub
		return replyMapper.getList(bno, cri);
	}
	/**
	 * Transactional
	 * 		서비스 로직에 대한 트랜젝션 처리를 지원
	 * 		오류 발생시 롤백
	 */
	@Transactional
	@Override
	public int insert(ReplyVO vo) {
		// 댓글 입력시 Board 테이블의 댓글수(replyCnt)를 1증가 시켜 줍니다.
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return replyMapper.insert(vo);
	}

	@Transactional
	@Override
	public int delete(int rno) {
		
		ReplyVO vo = replyMapper.getOne(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		// boardMapper.updateReplyCnt(vo.getBno(), -1);
		return replyMapper.delete(rno);
	}

	@Override
	public int update(ReplyVO vo) {
		// TODO Auto-generated method stub
		return replyMapper.update(vo);
	}

	@Override
	public int totalCnt(int bno) {
		// TODO Auto-generated method stub
		return replyMapper.totalCnt(bno);
	}
}
