package pack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("world.do")
public class WorldController{
	@RequestMapping(method=RequestMethod.GET) // get 방식으로 받는 다는 것.
	public ModelAndView sbs() {
		System.out.println("WorldController 처리");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("list2");
		view.addObject("msg", "월드:get");
		return view;
	}
	
	@RequestMapping(method=RequestMethod.POST) // post 방식으로 받는 다는 것.
	public ModelAndView ytn() {
		System.out.println("WorldController 처리");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("list2");
		view.addObject("msg", "월드");
		return view;
	}

}
