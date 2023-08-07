package com.boardService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boardService.constant.Method;
import com.boardService.domain.BoardDTO;
import com.boardService.paging.Criteria;
import com.boardService.service.BoardService;
import com.boardService.util.UiUtils;

// 클라이언트의 요청/응답을 처리하는 컨트롤러임을 선언
@Controller
public class BoardController extends UiUtils {
	
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
	public String registerBoard(final BoardDTO params, Model model) {
		try {
			boolean isRegisterd = boardService.registerBoard(params);
			if (isRegisterd == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.","/boardService/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리과정에 문제가 발생하였습니다.","/boardService/list.do", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.","/boardService/list.do", Method.GET, null, model);
		}
		
		return showMessageWithRedirect("게시글 등록이 완료되었습니다.","/boardService/list.do", Method.GET, null, model);
	}
	
	// ModelAttribute : 파라미터로 전달받은 객체를 자동으로 뷰까지 전달
	@GetMapping(value = "/boardService/list.do")
	public String openBoardList(@ModelAttribute("criteria") BoardDTO params, Model model) {
		List<BoardDTO> boardList = boardService.getBoardList(params);
		model.addAttribute("boardList", boardList);
		
		return "boardService/list";
	}
	
	@GetMapping(value = "/boardService/view.do")
	public String openBoardDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null ) {
			// 올바르지 않은 접근
			return "redirect:/boardSerivce/list.do";
		}
		
		BoardDTO board = boardService.getBoardDetail(idx);
		if(board == null || "Y".equals(board.getDeleteYn())) {
			// 없는 게시글이거나 삭제된 게시글
			return "redirect:/boardSerivce/list.do";
		}
		model.addAttribute("board", board);
		
		return "boardService/view";
	}
	
	@PostMapping(value = "/boardService/delete.do")
	public String deleteBoard(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.","/boardService/list.do", Method.GET, null, model);
		}
		
		try {
			boolean isDeleted = boardService.deleteBoard(idx);
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.","/boardService/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리과정에 문제가 발생하였습니다.","/boardService/list.do", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.","/boardService/list.do", Method.GET, null, model);
		}
		
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.","/boardService/list.do", Method.GET, null, model);
	}
}
