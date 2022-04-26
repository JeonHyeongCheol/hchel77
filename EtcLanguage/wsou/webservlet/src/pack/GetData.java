package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetData
 */
@WebServlet("/GetData")
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name"); // java 변수 = html 넘어오는 값 
		String addr = request.getParameter("addr");
		String age = request.getParameter("age");
		
		//System.out.println(name + " " + addr + " " + age);
		
		response.setContentType("text/html;charset=utf-8"); // 넘기는 문서가 html이고 문자는 utf-8이라는 것.
		
		PrintWriter out = response.getWriter(); // 입출력을 하기위해 도와주는 클래스 및 메소드.
		out.println("<html><body>");
		out.println("<h1>get 결과</h1>");
		out.println("이름은 " + name + "<br>");
		out.println("주소는 " + addr + "<br>");
		out.println("나이는 " + age + "<br>");
		out.println(calcAge(age) + "<br>");
		out.println("<a href='gettest.html'>다시입력</a>");
		out.println("<br><a href='mbc/sbs/star.html'>가자 별에게로</a>");
		out.println("</body></html>");
		out.close();
	}
	
	private String calcAge(String age) {
		String result = "";
		
		if(Integer.parseInt(age) < 20) {
			result = "10대 입니다";
		} else if(Integer.parseInt(age) <30) {
			result = "20대 입니다";
		} else if(Integer.parseInt(age) <40) {
			result = "30대 입니다";
		} else if(Integer.parseInt(age) <50) {
			result = "40대 입니다";
		}
		return result;
		/* 다른 방법들
		result = (Integer.parseInt(age)) / 10 + "0대"; // 첫번째 방법
		result = ((Integer.parseInt(age)) / 10 ) * 10) + "0대"; // 두번째 방법
		return result;
		
		int result = (Integer.parseInt(age)) / 10; // 세번째 방법
		return (result * 10) + "대";
		
		reuslt = (interget.parseInt(age)).substring(0,1) + "대"; // 네번째 방법
		*/
	}

}
