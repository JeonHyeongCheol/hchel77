package pack.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import pack.model.HelloModel;

public class HelloController implements Controller {

	private HelloModel helloModel;
	
	public void setHelloModel(HelloModel helloModel) {
		this.helloModel = helloModel;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		// 모델 수행
		String ss = helloModel.process();
		
		// 방법 1 : 
		//ModelAndView modelAndView = new ModelAndView("hello");
		
		// 방법2 :
		ModelAndView modelAndView = new ModelAndView();
		
		// redirect
		//modelAndView.setViewName("redirect:/views/hello.jsp"); // 핸들러 맵핑을 만나지 않음. ViewResolver를 만나지 않기 때문에 풀 Path를 써야 함.
		// redirect 방식을 쓸 경우 값을 가져 갈 수 없음. forward 방법을 써야 함.
		
		// forward
		modelAndView.setViewName("hello"); // forward
		
		// 모델 수행
		modelAndView.addObject("result", ss);
		
		return modelAndView;
	}

}
