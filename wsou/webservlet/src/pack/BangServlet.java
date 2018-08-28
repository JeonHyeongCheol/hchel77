package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BangServlet
 */
@WebServlet("/BangServlet")
public class BangServlet extends HttpServlet {
	
	// Maria DB 추가
	//	create table guest(
	//			code int primary key auto_increment, //auto_incrment는 번호 자동 증가
	//			name varchar(12) not null,
	//			subject varchar(20),
	//			content text) charset=utf8;
	

	
	private static final long serialVersionUID = 1L;
    
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public void init(ServletConfig config) throws ServletException {
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			pstmt = conn.prepareStatement("insert into guest(name, subject, content) values(?,?,?)");
		} catch(Exception e) {
			System.out.println("연결 오류 : " + e);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		try {
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			//response.sendRedirect("bang/bang_main.html"); // 자료 입력후 돌아감.
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<b>" + name + "님 등록 완료</b>");
			out.println("<br><a href='bang/bang_main.html'>새글 입력</a>");
			out.println("<br><a href='BangList'>글 보기</a>");
			out.println("</body></html>");
			out.close();
		} catch (Exception e) {
			System.out.println("doPost err : " + e);
		} 
	}
	
	public void destroy() {
		try{		
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(Exception e) {
				System.out.println("처리 실패 : " + e);
			}
	}

}
