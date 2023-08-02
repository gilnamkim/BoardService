package com.boardService.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.boardService.constant.Method;

// 사용자 편의기능을 포함한 컨트롤러
@Controller
public class UiUtils {
	
	// message : 클라이언트에게 error 등 여러 메시지를 전달
	// redirectUri : 게시글작성 > 작성완료메시지전달 > 리스트페이지로 redirect 기능을 수행
	// method : Method Enum 클래스에 선언한 HTTP 요청 메서드
	// params : 화면으로 전달할 파라미터, 파라미터의 개수는 페이지마다 바뀌므로 여러 데이터를 Key, value 형태로 담을 수 있는 Map 활용
	// model : 화면으로 보낼 파라미터를 모두 담는 객체
	public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
										  @RequestParam(value = "redirectUri", required = false) String redirectUri,
									      @RequestParam(value = "method", required = false) Method method,
										  @RequestParam(value = "params", required = false) Map<String, Object> params, 
										  Model model) {
		
		model.addAttribute("message", message);
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("method", method);
		model.addAttribute("params", params);
		
		return "utils/message-redirect";
	}
}
