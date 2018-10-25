package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.MemberDao;
import pack.model.MemberDto;

@Controller
public class DetailController {
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("detail")
	public ModelAndView detail(@RequestParam("id") String id) { // 각 멤버를 불러오는 메소드
		MemberDto dto = memberDao.getMember(id);
		return new ModelAndView("detail", "member", dto); // detail.jsp, attribute 키 값은 member, dto를 들고 감. 
	}
}
