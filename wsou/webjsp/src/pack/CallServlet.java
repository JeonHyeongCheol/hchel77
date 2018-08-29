package pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.DMUL;

/**
 * Servlet implementation class CallServlet
 */
@WebServlet("/irum.do")
public class CallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String data = request.getParameter("data");
		// 넘어온 자료를 이런저런 작업을 하다가...
		
		// 파일 호출방법 1 : redirect - client를 통해 서버에 있는 파일 호출
		response.sendRedirect("calljsp1.jsp?data=" + data);
		// 클라이언트 요청에 의해서
		// irum.do에 오자마자 jsp를 불러서 irum.do는 거의 보이지 않고 calljsp1로 넘어감.
		// 이런 방식이 redirect 방식.
		
		// 파일 호출방법 2 : forward - 서버에서 서버에 있는 파일 호출
		//request.setAttribute("mykey", data); 
		// data에 값을 mykey에 넣어줌. 계속 넣을 수 있음.
		// jsp갈 때는 obj 타입으로 넘어감
		// request는 컨테이너 기능이 있음
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("calljsp1.jsp"); 
		// 서버에서 서버에 파일을 불러 출력되기 때문에 url은 서버url이 됨.
		
		//dispatcher.forward(request, response); // calljsp1에 값을 넘겨주면서 jsp를 불러서 서버에서 출력 해주는 것.
		// request : 클라이언트로 부터 받아오는 값, response : 받아 온 값을 넘겨(보내)주는 값
		// 위에 request.setAttribute에 있는 값이 request에 넘어감.
		// service 메소드를 부르기 때문에 받아 오는 값을 넘겨 줘야 함.
		// forward는 서비스 기능을 오버라이딩 함.
		
		//request.getRequestDispatcher("calljsp1.jsp").forward(request, response); // 호출 2방식이랑 똑같은데 줄인 것.
	}

}
