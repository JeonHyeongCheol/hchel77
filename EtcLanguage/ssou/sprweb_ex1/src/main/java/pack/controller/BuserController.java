package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.Jikwon_Buser_Inter;

@Controller
public class BuserController{
	
	@Autowired
	private Jikwon_Buser_Inter jikwon_Buser_Inter;
	
	@RequestMapping("buser")
	public ModelAndView selectBuser(@RequestParam("buser") String buser) {
		return new ModelAndView("buser", "buser", jikwon_Buser_Inter.selectBuser(buser));
	}
}
