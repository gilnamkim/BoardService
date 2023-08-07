package com.boardService.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardService.domain.BoardDTO;
import com.boardService.mapper.BoardMapper;
import com.boardService.paging.Criteria;
import com.boardService.paging.PaginationInfo;

// 비즈니스 로직을 담당하는 서비스 클래스임을 명시
// boardService 인터페이스를 상속(implements)받아 메서드를 구현
@Service
public class BoardServiceImpl implements BoardService {
	
	// boardMapper 객체 생성
	@Autowired
	private BoardMapper boardMapper;
	
	// 
	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		
		// testOfInsert와 testOfUpdate를 구현
		// idx의 유무에 따라 insert, update를 수행
		if(params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	// testOfSelectDetail을 구현
	// 출력하지 않고 바로 BoardDTO형태로 리턴한다
	@Override
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}
	
	// testOfDelete를 구현
	// board객채를 만들어서 해당 idx가 null이 아니고 delete_yn이 'N'일 경우
	// deleteBoard 수행
	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		// 조건식을 board.getDeleteYn().equals("N")로 사용해도 결과는 같지만
		// null-safe관점에서 차이가 있다
		// "N".equals(board.getDeleteYn())는 null인 경우에도 nullpointexception이 발생하지 않는다
		// java에서는 null이 아닌 문자열과 null을 비교할 때 예외를 발생시키지 않는다
		if(board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {
		// List를 null로 초기화해도 작동은 하지만 collections이 몇가지 장점이 있다
		// 1.불변성 : 리스트를 반환하는 메서드가 빈 리스트를 반환하더라도 클라이언트에서 해당 리스트를 변경할 수 없다
		// 2.메모리절약 : 싱글턴 패턴을 사용하여 여러번 호출되더라도 새로만들지 않고 같은 빈 리스트 객체를 반환한다
		// 3.안전성 : null이 아닌 빈 리스트이기 때문에 nullPointException을 방지할 수 있다(null체크 필요없음)
		List<BoardDTO> boardList = Collections.emptyList();
		
		int boardTotalCount = boardMapper.selectBoardTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(boardTotalCount);
		
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList(params);
		}
		
		return boardList;
	}

}
