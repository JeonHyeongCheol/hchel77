package kr.co.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.go")
public class ControllerServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command"); // Client에서 보내오는 값을 가짐.
		String viewName = "view/";
		
		CommandInter inter = null; // 인터페이스 객체를 생성하고 null 값을 줌.
		
		try {
			if(command.equals("message")) {
				inter = new MessagePro(); // 사실은 싱글톤 처리 해야 함. CommandInter 아래 MessagePro가 있기 때문에 데이터 값을 가져 올 수 있음.
			} else if(command.equals("date")) {
				inter = new DatePro();
			}
			viewName += inter.showData(request, response); // CommandInter에서 올 때 자식에서 오는 값을 가져옴. 리턴값이 viewName 임.
			request.getRequestDispatcher(viewName).forward(request, response);
		} catch (Exception e) {
			System.out.println("ControllerServlet err : " + e);
		}
	}

}
