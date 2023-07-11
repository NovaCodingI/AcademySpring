package com.ikik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikik.mapper.ReplyMapper;
import com.ikik.vo.Criteria;
import com.ikik.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public List<ReplyVO> getList(int bno, Criteria cri) {
		// TODO Auto-generated method stub
		return replyMapper.getList(bno, cri);
	}

	@Override
	public int insert(ReplyVO vo) {
		// TODO Auto-generated method stub
		return replyMapper.insert(vo);
	}

	@Override
	public int delete(int rno) {
		// TODO Auto-generated method stub
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
