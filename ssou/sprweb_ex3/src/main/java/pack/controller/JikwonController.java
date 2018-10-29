package pack.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.JikwonDto;
import pack.model.Gogek_Jikwon_Inter;

@Controller
public class JikwonController {
	
	@Autowired
	@Qualifier("gogek_Jikwon_Impl")
	private Gogek_Jikwon_Inter gogek_Jikwon_Inter;
	
	@RequestMapping("jikwon")
	public ModelAndView getList(HttpSession session, HttpServletRequest request, HttpServletResponse response) { // session을 달아야 값을 받아 올 수 있음.
		
		String damsano = (String)session.getAttribute("damsano");
		
		JikwonDto jikwon = gogek_Jikwon_Inter.jikwon(damsano);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("jikwon", jikwon);
		modelAndView.setViewName("jikwon");
		return modelAndView;
	}
}
