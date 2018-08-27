package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostData
 */
@WebServlet("/PostData")
public class PostData extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 받아 오는데 doGet, doPost가 다르면 405Err 뜸.
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String irum = request.getParameter("name");
		//System.out.println(irum); // Post방식은 한글이 깨짐. 인코딩 해줘야 함.
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>*결과 확인*</h2>");
		out.println("이름은 " + irum + "<br>");
		
		String juso[] = request.getParameterValues("addr"); // name이 중복이면 배열로 받아서 출력해줘야 함. 배열로 받을 때는 ParameterValues를 사용하여야 함.
		out.println("주소는 " + juso[0] + " " + juso[1] + "<br>");
		
		// checkbox
		// 체크박스는 안들어 오는 경우도 있기 때문에 예외처리를 하여야 함.
		try {
			String lan[] = request.getParameterValues("lan");
			for(String a:lan) {
				out.print(a + " ");
			}
			out.println("<br>");
		} catch (Exception e) {
			out.println("<br>언어 선택 안됨</br>");
		}
		
		// radiobutton
		String tr = request.getParameter("tr");
		out.println("교통수단은 " + tr + "<br>");
		
		// select
		String os = request.getParameter("os");
		out.println("운영체제는 " + os + "<br>");
		
		// hidden
		String hobby = request.getParameter("hobby");
		out.println("취미는 " + hobby);
		
		out.println("</body></html>");
		out.close();
		
	}

}
