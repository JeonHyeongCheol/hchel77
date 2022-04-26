package kr.mvc.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("*.do")
public class UserServlet extends HttpServlet {
	Controller controller = null;
	ModelAndView modelAndView = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			// 파일명을 요청정보로 사용.
			String ss = request.getRequestURI(); // URI 정보가 들어옴.
			// /mvc_crud/insa.do

			int i = ss.lastIndexOf("/"); // (/)에 대한 index 찾기.
			StringTokenizer st = new StringTokenizer(ss.substring(i + 1), "."); // (/)다음 부터 (.)까지의 글자를 찾음.
			ss = st.nextToken(); // 위에서 짜른 값을 가져옴.
			
			//System.out.println(ss);
			String command = ss;
			
			controller = getAction(command); // 들어오는 command 값에 따라 가져오는 값이 달라짐. 그 값은 controller가 가지게 됨.
			// System.out.println(controller); // loginAction에 대한 주소.
			modelAndView = controller.execute(request, response); // LoginAction에 들어가 execute 값을 가져와 modelandview가 가짐.
			
			// 파일 호출 방식에 따라 분리
			if(modelAndView.isRedirect()) { // modelAndview.isredirect()가 true일 경우 redirect 방식으로 false일 경우 forward 방식으로 한다는 것.
				// redirect
				response.sendRedirect(modelAndView.getViewName()); // 로그인 할 때만 redirect라고 생각 하면 빠름.
			} else { 
				// forward
				RequestDispatcher dispatcher = request.getRequestDispatcher(modelAndView.getViewName());
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("Service err : " + e);
		}
	}
	
	public Controller getAction(String command) throws Exception{
		if(command.equals("login")) {
			controller = new LoginAction(); // 들어오게 되면 loginaction에 대한 객체를 가짐.
		} else if(command.equals("list")) { // 로그인 후 command가 list일 경우.
			controller = new ListAction();
		} else if(command.equals("logout")) {
			controller = new LogoutAction();
		} else if(command.equals("insert")) {
			controller = new InsertAction();
		} else if(command.equals("view")) {
			controller = new ViewAction();
		} else if(command.equals("updateform")) {
			controller = new UpdateFormAction();
		} else if(command.equals("update")) {
			controller = new UpdateAction();
		} else if(command.equals("delete")) {
			controller = new DeleteAction();
		}
	
		return controller;
	}

}

