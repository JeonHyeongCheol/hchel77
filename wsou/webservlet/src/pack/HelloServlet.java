package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Helloservlet
 */
@WebServlet("/good.kor") 
// web.xml과 여기 둘다 활성화시 충돌 남. 하나만 해줘야하는데 여기에만 써도 충분히 실행 가능, web.xml을 잘 사용안하고 이런식으로 사용 많이 함.
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //HttpServletResponse로 인해 웹에 값을 줌.
    	// doPost하면 405 Err뜸. get이면 get방식, post면 post방식을 사용하여야 함.
    	// Java를 만들고 실행을 하면 WebContent에서 실행되는 것과 같다.(같은 선상에 있음)
    		
		//System.out.println("성공");
    	response.setContentType("text/html;charset=utf-8");
		
    	PrintWriter out = response.getWriter(); // 웹페이지를 실행시키면 밑에있는 코드들을 수행. 서블릿은 Java가 주임.
		out.println("<html><body>");
		out.println("<h1>안녕하세요</h1>");
		out.println("<b>반가워요</b><br>느낌이 중요!");
		out.println("<a href='http://www.daum.net'>daum</a>");
		out.println("</html></body>");
		out.close();
		}

	

}
