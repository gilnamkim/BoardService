package com.boardService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String openBoardWrite(Model model) {
		
		// Model 인터페이스를 활용해보자
		String title = "제목";
		String content = "내용";
		String writer = "Gilnam";
		
		// addAttribute메서드로 model객체에 매핑
		model.addAttribute("t", title);
		model.addAttribute("c", content);
		model.addAttribute("w", writer);
		
		
		// 예전에는 ModelAndView를 리턴했으나 최근에는 String문자열로 리턴
		// 접미사는 .html로 자동연결되기 때문에 생략
		return "boardService/write";
	}
}
