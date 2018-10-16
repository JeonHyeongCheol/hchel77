package pack2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HobbyModel;

//@WebServlet("/hobby.do")
//@WebServlet({"/hobby.do","/hobby2.do"}) // 여러 개일 경우 { } 괄호(배열)로 묶으면 됨.
//@WebServlet("*.do") // 확장자로도 가능
@WebServlet({"*.do", "*.kor"}) // 확장자도 여러 개일 경우 괄호로 묶을 수 있음.
public class hobbyController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hobby = request.getParameter("hobby");
		//System.out.println(hobby);
		
		HobbyModel model = HobbyModel.getinstance();
		
		ArrayList<String> list = model.getHobby(hobby); // hobby 값에 따라 list에 값이 달라짐. 모델을 갔다온 것.
		request.setAttribute("data", list);
		String viewName = "view_h/mvc_hobby.jsp";
		
		// 뷰 호출
		request.getRequestDispatcher(viewName).forward(request, response); // 두 줄로 쓸 수 있지만 간단하게 한 줄 사용.
	}
	
}
