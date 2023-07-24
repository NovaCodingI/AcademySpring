package com.ikik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ikik.vo.Criteria;
import com.ikik.vo.ReplyVO;

public interface ReplyMapper {
	
	/**
	 * 매개변수가 두개 이상의 파라메터로 전달 되는 경우
	 * 	Param 어노테이션을 사용
	 * 
	 * 방법은 여러가지
	 * 1. 새로운 객체를 만들어서 파라메터를 담는것 → 한개로 담을수있게하기
	 * 2. Param 어노테이션을 사용
	 * @param bno
	 * @param cri
	 * @return
	 */
	public List<ReplyVO> getList(@Param(value="bno")int bno, @Param(value="cri")Criteria cri);
	
	public int insert(ReplyVO vo);
	
	public int update(ReplyVO vo);
	
	public int delete(int rno);
	
	public int totalCnt(int bno);
	
	public ReplyVO getOne(int rno);

	public void deleteReplyList(int bno);
	
}
