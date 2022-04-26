package kr.mvc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class ListAction implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 모델과 통신
		ArrayList<UserDto> list = UserManager.getinstance().getUserAll(); // 클래스의 포함 관계를 생각할 것. (.)은 포함관계임.
		request.setAttribute("list", list);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list.jsp");
		modelAndView.setRedirect(false); // 로그인 후이기 때문에 redirect 방식을 쓰지않고 서버에 바로 뿌리기 위해 forward 방식을 사용해야 함.
		return modelAndView; // 넘어갈 때 setViewName는 list.jsp, setRedirect는 forward방식이기 때문에 false을 리턴 함.
	}

}
