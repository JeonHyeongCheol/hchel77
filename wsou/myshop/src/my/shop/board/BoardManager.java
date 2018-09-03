package my.shop.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardManager {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds; // 폴링 처리
	
	private int tot; // 전체 레코드 수
	private int pList; // 각 페이지 당 출력 레코드 수
	private int pageSu; // 전체 페이지 수
	
	public BoardManager() {
		try {
			Context context = new InitialContext();
			// dbcp(DB Context Pooling). 폴링처리
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("BoardManager err : " + e);
		}
	}
	
	public void saveData(BoardBean bean) {
		String sql = "insert into shopboard values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			System.out.println(bean.getName());
		} catch (Exception e) {
			System.out.println("saveData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
}

