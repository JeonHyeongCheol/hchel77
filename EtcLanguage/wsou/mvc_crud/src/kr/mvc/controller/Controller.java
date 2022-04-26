package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	// 추상 메소드 : Execute
	ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
