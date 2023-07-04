package com.ikik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ikik.vo.BoardVO;

public interface BoardMapper {

	// 인터페이스는 추상메서드만! 구현부분이 없습니다.
	// 인터페이스는 설계의 용도로 사용되는 경우가 많습니다.
	// implement는 MyBatis가 알아서 구현해줍니다.
	@Select("select * from tbl_board")
	public List<BoardVO> getList();

	// 이름으로 만들었으니까 메서드만 만들어주셔도 됩니다.
	public List<BoardVO> getListXml();
	
	// insert, delete, update 의 반환은 몇건이 처리되었는지 이기때문에 반환타입은 int 입니다.
	public int insert(BoardVO board);
	
	public int insertSelectKey(BoardVO board);
	
	public BoardVO getOne(int bno);
	
	// 별도의 반환타입을 지정하지 않아요
	public int delete(int bno);
	
	public int update(BoardVO board);
	
	public int getTotalCnt();
	
}
