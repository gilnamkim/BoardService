package com.boardService;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.boardService.domain.BoardDTO;
import com.boardService.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class MapperTests {
	
	// BoardMapper Bean 주입
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		// params라는 BoardDTO 객체를 새로 만들어
		BoardDTO params = new BoardDTO();
		// 객체 변수에 정보를 저장하고
		params.setTitle("1번 제목");
		params.setContent("1번 내용");
		params.setWriter("테스터");
		
		// params 객체로 boardMapper의 insertBoard쿼리를 실행시켜 결과값을 받아온다. 
		int result = boardMapper.insertBoard(params);
		System.out.println("결과는"+ result + "입니다.");
	}
	
	@Test
	public void testOfSelectDetail() {
		// board라는 BoardDTO객체를 새로만들어 인수값 1을 넣어 selectBoardDetail의 쿼리문을 실행합니다
		// 인수값 1에 해당하는 BoardDTO의 객체를 반환받아 board에 저장합니다
		BoardDTO board = boardMapper.selectBoardDetail((long) 1);
		try {
			// board를 Json화 시켜 boardJson을 만듭니다
			String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
			System.out.println("=====================================");
			System.out.println(boardJson);
			System.out.println("=====================================");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 제목 수정");
		params.setContent("1번 내용 수정");
		params.setWriter("홍길동");
		params.setIdx((long) 1);
		
		// result 는 쿼리가 실행된 횟수가 저장된다. 메서드한번 호출에 쿼리 1회 실행
		int result = boardMapper.updateBoard(params);
		System.out.println(result);

		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
				System.out.println("=====================================");
				System.out.println(boardJson);
				System.out.println("=====================================");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfDelete() {
		// 인수 1로 deleteBoard메서드를 호출하면 해당 인수의 게시글의 delete_yn이 'N'에서 'Y'로 변경된다.
		int result = boardMapper.deleteBoard((long) 1);
		if(result == 1) {
			// selectBoardDetail의 쿼리문 중 WHERE절에 delete_yn = 'N' 조건으로 인하여 Board는 리턴값이 없는 null이 반환된다
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
				System.out.println("=====================================");
				System.out.println(boardJson);
				System.out.println("=====================================");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testSelectList() {
		// 우선 delete_yn = 'Y'를 제외한 모든 게시글의 갯수를 카운트합니다
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		// 게시글 수가 1개라도 존재하면 리스트를 출력해야 합니다
		if(boardTotalCount > 0) {
			// selectBoardList메서드 호출로 받은 BoardDTO객체를 list로 만듭니다
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			// boardList가 비어있지 않은지 확인한 후
			if(CollectionUtils.isEmpty(boardList) == false) {
				// boardList에 저장된 BoardDTO객체인 board를 순서대로 출력합니다 
				for(BoardDTO board : boardList) {
					System.out.println("======================================");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("======================================");
				}
			}
		}
	}
}
