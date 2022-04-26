package pack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		System.out.println("TestController 수행");
		
		//return new ModelAndView("list"); // 방법1 : 줄 내용이 없어서 그냥 View파일 list.jsp만 가져감.
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("say", "스프링 웹이여 영원하라!"); // 속성값을 가지고 감.
		return modelAndView;
	}
}
