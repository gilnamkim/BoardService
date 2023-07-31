package com.boardService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardService.domain.BoardDTO;
import com.boardService.mapper.BoardMapper;

@SpringBootTest
class MapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 제목");
		params.setContent("1번 내용");
		params.setWriter("테스터");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("결과는"+ result + "입니다.");
	}
}
