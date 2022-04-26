package pack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class WorldController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		System.out.println("WorldController 처리");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("list2");
		view.addObject("msg", "월드");
		return view;
	}
}
