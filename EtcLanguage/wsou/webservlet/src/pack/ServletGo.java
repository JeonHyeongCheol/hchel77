package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletGo
 */
@WebServlet("/ServletGo")
public class ServletGo extends HttpServlet {
	private ServletPoham poham; // 포함관계..클래스를 끌고 오기때문에
	
	private static final long serialVersionUID = 1L;
	
	
	public void init(ServletConfig config) throws ServletException {
		// 클라이언트에서 최초로 들어올 때만 init을 수행함.
		// 이 자원은 쓰레드에 있어서 공유됨.
		//System.out.println("초기화");
		poham = new ServletPoham("홍길동");
	}

	
	public void destroy() {
		// 서버가 꺼질 때 수행.
		//System.out.println("destory : 마무리");
	}

	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 두번째로 들어오는 클라이언트부터는 계속 들어올 때마다 여기를 수행하게 됨.
		System.out.println("doGet : 요청 시 계속 수행");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost : 요청 시 계속 수행");

	}
	*/
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1순위 : service, 2순위 : doGet, doPost
		// service는 doGet, doPost를 둘다 받을 수 있음. 없을 경우에는 doGet, doPost 방식으로 받음.
		// doGet, doPost는 서로 같은 방식이여야지 가능.
		//System.out.println("Service : 요청 시 계속 수행");
		//doGet(request,response);
		response.setContentType("text/html;charset=utf-8"); // 넘기는 문서가 html이고 문자는 utf-8이라는 것.
		PrintWriter out = response.getWriter(); // 입출력을 하기위해 도와주는 클래스 및 메소드.
		out.println("<html><body>");
		out.println("<h1>서블릿 만세</h1>");
		int a = 10, b = 20;
		out.println("a : " + a + ", b : " + b );
		int tot = calc(a, b);
		out.println("<br>두 수의 합은 " + tot);
		
		//ServletPoham poham = new ServletPoham("홍길동"); 
		// 클라이언트가 들어올 때마다 객체가 생성되고 있음. 계속 만들면 부하가 걸리기 때문에 init()에 넣어 한번만 수행할 수 있도록 함.
		out.println("<br> 이름은 " + poham.getIrum());
		//System.out.println("객체의 주소 : " + poham.toString());
		poham.display(3, out);
		out.println("</body></html>");
		out.close();
	}
	
	private int calc(int su1, int su2) {
		return su1 + su2;
	}

}
