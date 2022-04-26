package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // 세션이 있으면 생성하지 않음
		if(session == null) return;
		
		ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");
		
		if(glist == null) return;
		
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<html><body>");
		out.println("<h1>결제처리</h1>");
		out.println("<table border='1' width='70%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		int tot = 0;
		for(int i = 0; i < glist.size(); i++) {
			
			Goods goods = glist.get(i);
			out.println("<tr><td>" + goods.getPname() + "</td>");
			out.println("<td>" + goods.getPrice() + "</td><tr>");
			tot += goods.getPrice();
		}
		out.println("<tr><td colspan='2'>결제총액 : " + tot + "</td></tr>");
		out.println("</table>");
		out.println("<br>고객님 감사합니다");
		out.println("<br><a href='shop_main.html'>새 마음으로 쇼핑</a>");
		
		// 세션 삭제
		//session.invalidate(); // 해당 고객의 세션을 모두 삭제
		session.removeAttribute("list"); // 해당 고객의 특정 세션만 삭제
		out.println("</body></html>");
		out.close();
	}

}
