package com.boardService.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.boardService.domain.BoardDTO;
import com.boardService.paging.Criteria;

@Mapper // DB와 통신하기 위한 에노테이션 : SQL문을 찾아 실행
public interface BoardMapper {
	
	public int insertBoard(BoardDTO params); // INSERT 쿼리 호출 메서드
	public BoardDTO selectBoardDetail(Long idx); // SELECT 쿼리 호출 메서드 : 1개의 게시글 조회
	public int updateBoard(BoardDTO params); // UPDATE 쿼리 호출 메서드
	public int deleteBoard(Long idx); // DELETE 쿼리 호출 메서드
	public List<BoardDTO> selectBoardList(Criteria criteria); // SELECT 쿼리 호출 메서드 : 여러개의 게시글 조회
	public int selectBoardTotalCount(Criteria criteria); // SELECT 쿼리 호출 메서드 : 삭제여부(deleteYn)의 값이 'N'인 지정된 게시글 조회
	
}
