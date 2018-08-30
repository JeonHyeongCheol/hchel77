package beanpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConnBean {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ConnBean() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	public ArrayList<SangpumDto> getDatas() {
		ArrayList<SangpumDto> list = new ArrayList<>(); // DB값 저장 위해 ArrayList 컬렉션 생성
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			
			String sql = "select code, sang, su, dan from sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SangpumDto dto = new SangpumDto(); // SangpumDto에 값을 저장 후 Arraylist에 넘김
				dto.setCode(rs.getString(1));
				dto.setSang(rs.getString(2));
				dto.setSu(rs.getInt(3));
				dto.setDan(rs.getInt(4));
				list.add(dto); // Arraylist에 넣음
			}
		} catch (Exception e) {
			System.out.println("getDatas err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// 
			}
		}
		return list;
	}
}
