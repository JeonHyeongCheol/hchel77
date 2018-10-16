package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		httpSession.removeAttribute("userid"); // 세션 삭제.
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login.jsp"); // 로그아웃 후 login 페이지로 접속.
		modelAndView.setRedirect(true); // redirect 방식.
		return modelAndView;
	}
}
