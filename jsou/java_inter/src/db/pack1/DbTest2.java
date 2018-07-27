package db.pack1;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DbTest2 {
	// secure coding 작업 : 별도 정보 파일 읽기
	private Connection conn; // DB 연결 객체
	private Statement stmt; // SQL문 실행
	private ResultSet rs; // select문의 결과 접근
	Properties properties = new Properties();

	public DbTest2() {
		try {
			properties.load(new FileInputStream("\\work\\jsou\\java_inter\\src\\db\\pack1\\dbmeta.properties"));
			Class.forName(properties.getProperty("driver"));
			conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
					properties.getProperty("passwd"));
			stmt = conn.createStatement();
/*
			// 자료 추가 -- auto commit;
			String isql = "insert into sangdata values(7,'꼬까알콘',2,1800)";
			//stmt.executeQuery(isql); // insert, update, delete
			//int re = stmt.executeUpdate(isql); //insert, update, delete // 성공하면 1
			//System.out.println(re);
			
		
			// ---  Transaction 처리(수동으로 커밋) 예) 은행 계좌이체 등-------
			conn.setAutoCommit(false); // 트랜잭션 시작. 커밋 꺼놓고.
			String isql = "insert into sangdata values(8,'브라보콘',3,1800)";
			stmt. executeUpdate(isql);
			// conn.rollback // 추가 취소
			//conn.rollback() // 추가 완료
			conn.setAutoCommit(true);// 트랜잭션 마무리
*/			
			// 자료 수정 -- auto commit
			
			String usql = "update sangdata set sang='형광펜', su=11 where code=3";
			int re = stmt.executeUpdate(usql);
			if(re > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("허걱 실패");
			}
			
			// 자료 삭제 -- auto commit
			
			String dsql = "delete from sangdata where code=7";
			re = stmt.executeUpdate(dsql);
			if(re > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			// 모든 자료 읽기
			
			String ssql = "select * from sangdata order by code asc";
			rs = stmt.executeQuery(ssql);
			int cou = 0;

			while (rs.next()) {
				System.out.println(rs.getString("code") + " " + 
									rs.getString("sang") + " " + 
									rs.getString("su") + " " + 
									rs.getString("dan"));
				cou++;
			}
			
			System.out.println("건수 : " + cou);
			
			// 일부 자료 읽기
			String masql = "select * from sangdata where sang like '마%'";
			rs = stmt.executeQuery(masql);


			while (rs.next()) { // 여러개일 때는 while, 하나일 때는 if 사용.
				System.out.println(rs.getString("code") + " " + 
									rs.getString("sang") + " " + 
									rs.getString("su") + " " + 
									rs.getString("dan"));
				cou++;
			}
			

		} catch (Exception e) {
			System.out.println("처리 오류" + e.getMessage());
			return;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) {
		new DbTest2(); // 생성자 부름

	}

}
