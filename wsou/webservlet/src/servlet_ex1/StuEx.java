package servlet_ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.Goods;

/**
 * Servlet implementation class StuEx
 */
@WebServlet("/StuEx")
public class StuEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int pnum = Integer.parseInt(request.getParameter("num"));
		String pname = request.getParameter("name");
		int pkor = Integer.parseInt(request.getParameter("kor"));
		int peng = Integer.parseInt(request.getParameter("eng"));
		
		HttpSession session = request.getSession(true);
		ArrayList<Stus> stulist = (ArrayList<Stus>)session.getAttribute("list");
		
		if(stulist == null) stulist = new ArrayList<>();
		
		stulist.add(new Stus(pnum, pname, pkor, peng));
		session.setAttribute("list", stulist);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		out.println("<html><body>");
		out.println("<h1>학생들 성적표</h1>");
		out.println("<table border='1' width='70%'>");
		out.println("<tr><th>번호</th><th>이름</th><th>국어</th><th>영어</th><th>총점</th></tr>");
		int cou = 0;
		for(int i = 0; i < stulist.size(); i++) {
			Stus stus = stulist.get(i);
			out.println("<tr><td>" + stus.getPnum() + "</td>");
			out.println("<td>" + stus.getPname() + "</td>");
			out.println("<td>" + stus.getPkor() + "</td>");
			out.println("<td>" + stus.getPeng() + "</td>");
			out.println("<td>" + stus.getSum() + "</td><tr>");
			cou++;
		}
		out.println("</table>");
		out.println("인원수 : " + cou + "명");
		out.println("<br><a href='servlet_ex1.html'>새로입력</a>");
		out.println("<br><a href='StuEx'>세션삭제</a>"); // get방식으로 자기자신을 부르면됨
		out.println("</body></html>");
		out.close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // 세션이 있으면 생성하지 않음
		if(session == null) return;
		
		ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");
	
		if(glist == null) return;
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		out.println("<html><body>");
		out.println("<h1>세션삭제완료</h1>");
		out.println("<br>");
		out.println("<h3>성적표가 삭제 되었습니다</h3>");
		session.removeAttribute("list");
		out.println("<br><a href='servlet_ex1.html'>새로입력</a>");
		out.println("</body></html>");
		out.close();
	}
}
