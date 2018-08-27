package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLogin
 */
@WebServlet("/CookieLogin")
public class CookieLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		// 쿠키를 읽어 쿠키가 있으면 , 없으면 로그인 화면 출력
		String id = null;
		String pwd = null;
		try {
			Cookie[] cookies = request.getCookies();
			for(int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();
				//System.out.println(name);
				if(name.equals("id")) { // 쿠키 값에 있는 id 확인.
					id = URLDecoder.decode(cookies[i].getValue(), "utf-8");
				}
				
				if(name.equals("pwd")) { // 쿠키 값에 있는 pwd 확인.
					pwd = URLDecoder.decode(cookies[i].getValue(), "utf-8");
				}
				
				System.out.println(id + " " + pwd);
				
				if(id != null && pwd != null) { // 값이 null이 아니면 로그인, 값이 null이면 로그인화면 출력.
					out.println(id + "님 쿠키를 통해 로그인 상태입니다");
					out.println("</body></html>");
					out.close();
					return; // 확인되면 return하여 메소드 탈출. 안그러면 밑에있는 라인을 다 읽게 됨.
				}
				
			}
		} catch (Exception e) {
			
		}
		// 
		out.println("** 로그인 **");
		out.println("<form method='post'>");
		out.println("i d : <input type='text' name='id'<br>");
		out.println("<br>");
		out.println("pwd : <input type='text' name='pwd'<br>");
		out.println("<br><br>");
		out.println("<input type='submit' value='전송'>");
		out.println("</form>");
		out.println("</body></html>");
		out.close();
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		if(id.equals("aa") && pwd.equals("11")) {
			try {
				id = URLEncoder.encode(id, "utf-8"); // 암호화
				Cookie idCookie = new Cookie("id", id); // 쿠키에 대한 객체 생성
				idCookie.setMaxAge(60 * 60 * 24 * 365); // 쿠키 1년간 유효
				
				pwd = URLEncoder.encode(pwd, "utf-8"); // 암호화
				Cookie pwdCookie = new Cookie("pwd", pwd); // 쿠키에 대한 객체 생성
				pwdCookie.setMaxAge(60 * 60 * 24 * 365); // 쿠키 1년간 유효
				
				response.addCookie(idCookie); // 클라이언트 컴에 저장
				response.addCookie(pwdCookie);
			} catch (Exception e) {
				// TODO: handle exception
			}
			out.println("로그인 성공");
		} else {
			out.println("로그인 실패");
		}
		
		out.println("</body></html>");
		out.close();
	}

}
