package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.Goods;

/**
 * Servlet implementation class ServletDb
 */
@WebServlet("/BangList")
public class BangList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public void init() throws ServletException { // 초기화 : init에서 한번만 함. // 최초접속자에게만 유효함. 그 뒤에 사용자는 한번 읽힌 내용으로 이용.
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			pstmt = conn.prepareStatement("select * from guest order by code desc");
		} catch(Exception e) {
			System.out.println("연결 오류 : " + e);
			return;
		}
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<html><body><h2>* 방명록 보기 *</h2>");
		try {
			rs = pstmt.executeQuery();
			out.println("<br><a href='bang/bang_main.html'>새 글 입력</a>");
			out.println("<table border='1' width='70%'>");
			out.println("<tr><th>번호</th><th>이름</th><th>제목</th><th>내용</th></tr>");
			while(rs.next()) {
				out.println("<tr><td>" + rs.getString(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td></tr>");
			}
			out.println("</table>");
			
		} catch (Exception e) {
			System.out.println("service err : " + e);
		}
		out.println("</body></html>");
		out.close();
}
	
	@Override
	public void destroy() { // 마지막에 servelt 종료 할 때 마무리
		try{		
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(Exception e) {
				System.out.println("처리 실패 : " + e);
			}
	}
}
