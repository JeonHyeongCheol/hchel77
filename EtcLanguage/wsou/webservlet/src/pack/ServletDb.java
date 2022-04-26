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
@WebServlet("/ServletDb")
public class ServletDb extends HttpServlet {
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
			pstmt = conn.prepareStatement("select * from sangdata");
		} catch(Exception e) {
			System.out.println("연결 오류 : " + e);
			return;
		}
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
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<html><body><h2>* 상품 자료 *</h2>");
			try {
				rs = pstmt.executeQuery();
				while(rs.next()) {
					out.println(rs.getString(1) + " " +
								rs.getString(2) + " " +
								rs.getString(3) + " " +
								rs.getString(4) + "<br>");
				}
			} catch (Exception e) {
				System.out.println("service err : " + e);
			}
			out.println("</body></html>");
			out.close();
	}

}
