package co.kr.auction.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class LoginClass {
	public boolean loginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		
		//로그인 안했을때 로그인으로 보내기
		if(session.getAttribute("m_id") == null) {
			//response.sendRedirect("../member/login");
			return true;
		}else {
			return false;
		}
	}
}
