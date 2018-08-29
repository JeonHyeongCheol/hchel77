package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest
 */
@WebServlet("/SessionTest")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session : 각 클라이언트의 정보를 웹서버 RAM에 저장
		
		HttpSession session = request.getSession(true); // 세션이 있으면 읽고 없으면 생성.
		//HttpSession session = request.getSession(false); // 세션이 있으면 읽고 없으면 생성안함.
		
		//session.setMaxInactiveInterval(10); // 20초간 유효 (기본 유효시간은 30분)
		
		if(session != null) { // 세션이 null 이 아니면.
			session.setAttribute("name", "홍길동");
		}
		
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<html><body>");
		out.println("session id : " + session.getId());
		out.println("<br> name 키로 저장된 값 :" + session.getAttribute("name"));
		out.println("</body></html>");
		out.close();
	}

}
