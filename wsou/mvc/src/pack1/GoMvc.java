package pack1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoMvc
 */
@WebServlet("/GoMvc")
public class GoMvc extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String msg;
		try {
			msg = request.getParameter("msg");
		} catch (Exception e) {
			msg = null;
		}
		
		String result = "", viewName = "";
		RequestDispatcher dispatcher = null;
		
		// msg가 들어오는 값에 따라 다른 화면을 출력. Spring은 기본적으로 이런식의 방식을 사용.
		if(msg == null || msg.equals("")) {
			result = "환영합니다"; // 모델을 수행한 결과라 가정
			viewName = "test1/gomvc_view1.jsp"; // 출력 파일명 설정. servlet에서도 경로 설정해 웹페이지 이동 가능. 경로 꼭 확인 할 것!
		} else if(msg.equals("korea")) {
			result = "한국이군요"; // 모델을 수행한 결과라 가정
			viewName = "test1/gomvc_view2.jsp"; 
		} else {
			result = "반가워요"; // 모델을 수행한 결과라 가정
			viewName = "test1/gomvc_view3.jsp"; 
		}
		
		dispatcher = request.getRequestDispatcher(viewName); // 보여줄 view URL을 주는 것.
		request.setAttribute("result", result); // 속성값을 줌.
		dispatcher.forward(request, response); // 값을 넘겨주기위해 
		
		//dispatcher.forward(request, response); 
		// request : 클라이언트로 부터 받아오는 값, response : 받아 온 값을 넘겨(보내)주는 값
		// 위에 request.setAttribute에 있는 값이 request에 넘어감.
		// service 메소드를 부르기 때문에 받아 오는 값을 넘겨 줘야 함.
		// forward는 서비스 기능을 오버라이딩 함.
	}

}
