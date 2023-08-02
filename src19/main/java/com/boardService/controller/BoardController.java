package com.boardService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boardService.domain.BoardDTO;
import com.boardService.service.BoardService;

// 클라이언트의 요청/응답을 처리하는 컨트롤러임을 선언
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// spring4.3 이전버전에서는 @RequsetMapping(value = "/write" method= RequestMethod.GET)
	// 이런식으로 써야했으나 코드가 간결해지고 GET요청에 대한 의도가 분명해졌다
	// Model 인터페이스는 데이터를 view로 전달한다
	@GetMapping(value = "/boardService/write.do")
	public String openBoardWrite(@RequestParam(value = "idx", required = false)Long idx, Model model) {
		
		if(idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if(board == null) {
				return "redirect:/boardService/list.do";
			}
			model.addAttribute("board", board);
		}
		
		// 예전에는 ModelAndView를 리턴했으나 최근에는 String문자열로 리턴
		// 접미사는 .html로 자동연결되기 때문에 생략
		return "boardService/write";
	}
	
	@PostMapping(value = "/boardService/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegisterd = boardService.registerBoard(params);
			if (isRegisterd == false) {
				
			}
		} catch (DataAccessException e) {
			
		} catch (Exception e) {
			
		}
		
		// 클라이언트가 boardService/list.do로 요청하도록 유도
		return "redirect:/boardService/list.do";
	}
	
	@GetMapping(value = "/boardService/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "boardService/list";
	}
}
