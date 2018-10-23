package pack.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import pack.model.HelloModel;

@Controller
//@RequestMapping("hello.do")
@RequestMapping({"world.do", "h*", "kbs/abc.do", "mbc", "sbs"})
public class HelloController{
	
	@Autowired
	private HelloModel helloModel;
	
	/*
	@RequestMapping(method = RequestMethod.GET) // 메소드를 맵핑 할 때, 위에서 컨트롤러 자체 맵핑 대상이면 메소드라고 써줄 것. 되도록이면?
	public ModelAndView handleRequest() {
		
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
	*/
	
	
	// 밑에 두 개에 Method는 리턴되는 View 파일명이 없으면 들어오는 요청명에 따라 View 파일명을 불러옴.
	/*
	@RequestMapping(method = RequestMethod.GET)
	public Map<String , Object> abc() { // Map타입에 리턴 값이 String, object
		String ss = helloModel.process(); // 값 가져옴.
		
		HashMap<String, Object> map = new HashMap<>(); // HashMap은 Map 파생 클래스.
		map.put("result", ss); // hashMap은 ss 값을 들고 result값으로 리턴함.
		// 요청명이 뷰파일명으로 고정
		return map;
	}
	*/
	
	@RequestMapping(method = RequestMethod.GET)
	public Model abc(Model model) {
		String ss = helloModel.process();
		model.addAttribute("result", ss);
		// 요청명이 뷰파일명으로 고정
		return model;
	}
}
