package com.boardService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
