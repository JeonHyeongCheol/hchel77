package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.mvc.model.UserManager;

public class LoginAction implements Controller{ 
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception { // ModelAndView 반환.
		String id = request.getParameter("userid");
		String pwd = request.getParameter("password");
		//System.out.println(id + " " + pwd);
		
		// 모델과 통신
		UserManager manager = UserManager.getinstance();
		boolean b = manager.login(id, pwd);
		
		ModelAndView modelAndView = new ModelAndView(); // ModelAndView 객체 생성. viewName을 리턴해주기 위해.
		
		if(b) { // 로그인 성공
			HttpSession httpSession = request.getSession(true); // 있으면 읽고, 없으면 만듬.
			httpSession.setAttribute("userid", id); // 로그인 체크를 위해 세션값을 줌.
			modelAndView.setViewName("list.do"); // 로그인 성공하면 Servlet으로 감.
		} else { // 로그인 실패
			modelAndView.setViewName("loginfail.html");
		}
		
		modelAndView.setRedirect(true); // 넘겨줄 째 redirect 방식으로 뿌려준다는 뜻. forward방식으로 해주면 안됨. forward는 서버에서 바로 뿌려주는 것이기 때문에.
		return modelAndView;
	}
}
