package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class DeleteAction implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		
		int re = UserManager.getinstance().deleteData(userid);
		
		ModelAndView modelAndView = new ModelAndView();
		if(re > 0) {
			modelAndView.setViewName("list.do");			
		} else {
			modelAndView.setViewName("fail.html");
		}
		modelAndView.setRedirect(true);
		return modelAndView;
	}
}
