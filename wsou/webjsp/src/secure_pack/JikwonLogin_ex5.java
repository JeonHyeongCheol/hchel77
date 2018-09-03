package secure_pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beanpack.JikwonDto_ex3;

public class JikwonLogin_ex5 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public JikwonLogin_ex5() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	public boolean loginCheck(String jikwon_no, String jikwon_name) {
		boolean b = false;
		
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			String sql = "select * from jikwon where jikwon_no=? and jikwon_name=?";
			//System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jikwon_no);
			pstmt.setString(2, jikwon_name);
			rs = pstmt.executeQuery();
			
			b = rs.next(); // 자료가 있으며  true, 없으면 false.
			
		} catch (Exception e) {
			System.out.println("loginCheck err : " +e  );
		}
		return b;
	}
	
	public ArrayList<GogekDto_ex5> getDatas() {
		ArrayList<GogekDto_ex5> gogeklist = new ArrayList<>();
		
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			
			String sql = "select gogek_no, gogek_name, gogek_jumin, gogek_tel from gogek";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, jikwon_no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				GogekDto_ex5 dto = new GogekDto_ex5();
				dto.setGogek_no(rs.getInt(1));
				dto.setGogek_name(rs.getString(2));
				dto.setGogek_jumin(rs.getString(3));
				dto.setGogek_tel(rs.getString(4));
				gogeklist.add(dto);
				
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

		return gogeklist;
	}
	
	public ArrayList<GogekDto_ex5> getSelDatas(String jikwon_no) {
		ArrayList<GogekDto_ex5> gogeklist = new ArrayList<>();
		
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			
			String sql = "select gogek_no, gogek_name, gogek_jumin, gogek_tel from gogek where gogek_damsano=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jikwon_no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				GogekDto_ex5 dto = new GogekDto_ex5();
				dto.setGogek_no(rs.getInt(1));
				dto.setGogek_name(rs.getString(2));
				dto.setGogek_jumin(rs.getString(3));
				dto.setGogek_tel(rs.getString(4));
				gogeklist.add(dto);
				
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

		return gogeklist;
	}
}
