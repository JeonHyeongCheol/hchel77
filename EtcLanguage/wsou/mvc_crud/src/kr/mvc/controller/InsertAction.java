package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class InsertAction implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		UserForm userForm = new UserForm();
		userForm.setUserid(request.getParameter("userid"));
		userForm.setPassword(request.getParameter("password"));
		userForm.setName(request.getParameter("name"));
		userForm.setEmail(request.getParameter("email"));
		
		UserManager manager = UserManager.getinstance();
		int re = manager.insertData(userForm);
		
		ModelAndView modelAndView = new ModelAndView();
		if(re > 0) {
			modelAndView.setViewName("list.do");			
		} else {
			modelAndView.setViewName("fail.html");
		}
		modelAndView.setRedirect(true); // 파일을 불러야하기 때문에 redirect 사용.
		
		return modelAndView;
	}
}
