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
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pname = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		
		HttpSession session = request.getSession(true); // 세션이 없으면 만듬
		//ArrayList<Goods> glist = new ArrayList<Goods>(); // 상품에 정보를 담기위해 컬렉션 사용
		ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list"); // list라는 키값에다가 넣어줌.
		// list에 세션값이 있는지 없는지 확인. 그 값은 컬렉션을 이용하여만들어놓은 곳에서 확인 할 수 있음.
		
		if(glist == null) glist = new ArrayList<>(); // 값이 없으면 객체를 만듬. 두번째 부터는 안만들어도 됨.
		
		glist.add(new Goods(pname,price)); // 상품을 추가해줌.
		session.setAttribute("list", glist); // glist로 가져온 값을 list라는 키값에 넣어줌.
		
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<html><body> ☞" + pname + " 구입하셨습니다☜");
		out.println("<h1>장바구니 결과</h1>");
		out.println("<a href='shop_main.html'>계속 쇼핑</a>");
		out.println("<br><a href='Buy'>결제하기</a><br>");
		out.println("<table border='1' width='70%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		for(int i = 0; i < glist.size(); i++) {
			
			Goods goods = glist.get(i);
			out.println("<tr><td>" + goods.getPname() + "</td>");
			out.println("<td>" + goods.getPrice() + "</td><tr>");
		}
		out.println("</table></body></html>");
		out.close();
	}

}
