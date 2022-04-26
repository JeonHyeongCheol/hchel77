package pack.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import pack.model.JikwonDto;
import pack.model.JikwonInter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("jikwonImpl")
	private JikwonInter jikwonInter;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goLogin() {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String submit(HttpSession session, @RequestParam("no") String no, @RequestParam("name") String name) {
		JikwonDto dto = jikwonInter.getLoginInfo(no);
		if(dto != null) { // dto값이 있으면
			String retName = dto.getJikwon_name();
			if(retName.equals(name)) {
				session.setAttribute("name", retName);
			}
		}
		return "redirect:/jikwonlist";
	}
	
	@RequestMapping("logout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";
	}
}
