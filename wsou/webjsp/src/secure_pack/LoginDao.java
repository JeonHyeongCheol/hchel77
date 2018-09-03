package secure_pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public LoginDao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	public boolean loginCheck(String no, String name) {
		boolean b = false;
		
		try {
			/* secure_coding을 안했을 때.
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			String sql = "select * from jikwon where jikwon_no='" + no + "' and jikwon_name='" + name + "'";
			//System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			*/
			
			
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			String sql = "select * from jikwon where jikwon_no=? and jikwon_name=?";
			//System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			
			b = rs.next(); // 자료가 있으며  true, 없으면 false.
			
		} catch (Exception e) {
			System.out.println("loginCheck err : " +e  );
		}
		return b;
	}
}
