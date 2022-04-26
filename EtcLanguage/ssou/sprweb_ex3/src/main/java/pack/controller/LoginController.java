package pack.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import pack.model.JikwonDto;
import pack.model.GogekDto;
import pack.model.Gogek_Jikwon_Inter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("gogek_Jikwon_Impl")
	private Gogek_Jikwon_Inter gogek_Jikwon_Inter;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goLogin() {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String submit(HttpSession session, @RequestParam("name") String name, @RequestParam("jumin") String jumin) {
		GogekDto dto = gogek_Jikwon_Inter.getLoginInfo(name);
		if(dto != null) { // dto값이 있으면
			String rejumin = dto.getGogek_jumin();
			String damsano = dto.getGogek_damsano();
			if(rejumin.equals(jumin)) {
				session.setAttribute("name", name);
				session.setAttribute("damsano", damsano);
			}
		}
		return "redirect:/jikwon";
	}
	
	@RequestMapping("logout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";
	}
}