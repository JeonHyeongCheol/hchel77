package co.kr.auction.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.kr.auction.model.MemberDaoInter;
import co.kr.auction.model.MemberDto;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("memberDaoImpl")
	private MemberDaoInter memberDaoInter;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String goLogin() {
		return "member/login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String summitLogin(HttpSession session,
			@RequestParam("m_id") String id,
			@RequestParam("m_passwd") String passwd) {
		MemberDto dto = memberDaoInter.myLoginInfo(id);
		if(dto != null) {
			String retpasswd = dto.getM_passwd();
			if(retpasswd.equals(passwd)) {
				session.setAttribute("memlike", memberDaoInter.memlike(id).getM_like());
				session.setAttribute("m_id", id);
				return "redirect:/init.jsp";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}
	
	@RequestMapping(value = "logout", method=RequestMethod.GET)
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/init.jsp";
	}
}





