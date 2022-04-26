package db.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//PreparedStatement 와 Statement
//http://devbox.tistory.com/entry/Comporison

// 왠만하면 preparedStatement를 사용하도록!!
public class DBTest11Prepared {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public DBTest11Prepared() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			// 전체 자료 보기
			String sql = "select * from sangdata order by code";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " +
								   rs.getString(2) + " " +
								   rs.getString(3) + " " +
								   rs.getString(4) + " " );
			}
			
			System.out.println();
			
			// 부분 자료 읽기
			String co = "1"; // 외부에서 코드를 받는다 가정하고
			
			//sql = "select * from sangdata where code=" + co; // 이렇게 쓸 경우 보안에 문제가 있음. 
			sql = "select * from sangdata where code=?"; // statement는 물음표를 사용할 수 없음.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, co); // ? 에 값넣어 주기.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("code") + " " +
						   rs.getString("sang") + " " +
						   rs.getString("su") + " " +
						   rs.getString("dan") + " " );
			} else {
				System.out.println("그런 자료는 없어요~");
			}
			
			// 자료 추가
			/*
			String isql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(isql);
			pstmt.setString(1, "10");
			pstmt.setString(2, "신상품");
			pstmt.setInt(3, 100);
			pstmt.setString(4, "5000");
			
			int re = pstmt.executeUpdate();
			
			if(re == 1) {
				System.out.println("추가 성공");
			} else {
				System.out.println("추가 실패");
			}
			*/
			
			// 자료 수정
			/*
			String usql = "update sangdata set sang=?, su=?, dan=? where code=?";
			pstmt = conn.prepareStatement(usql);
			pstmt.setString(1, "초코과자");
			pstmt.setString(2, "8");
			pstmt.setInt(3, 3000);
			pstmt.setString(4, "1");
			
			int re = pstmt.executeUpdate();
			
			if(re == 1) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			*/
			
			// 자료 삭제
			String dsql = "delete from sangdata where code=?";
			pstmt = conn.prepareStatement(dsql);
			pstmt.setString(1, "10");
			
			if(pstmt.executeUpdate() > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			System.out.println("err : " + e);
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
	
	public static void main(String[] args) {
		new DBTest11Prepared();
	}
}
