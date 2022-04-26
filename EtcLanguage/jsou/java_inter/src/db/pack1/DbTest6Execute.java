package db.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest6Execute {

	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	public DbTest6Execute() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			process();
		} catch (Exception e) {
			System.out.println("DbTest6Execute err :" + e);
		} 
	}
	
	private void process() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			stmt = conn.createStatement();
			
			boolean b = false;
			// execute(SQL) : select는 true, 나머지는 false를 반환.
			
			b = stmt.execute("update sangdata set sang='소화기' where code = 1");
			System.out.println("update 후 : " + b); // 업데이트는 이루어지지만  execute는 false 상태. 업데이트와 select를 같이 쓸 때 select만 거를 때(?).
			int re = stmt.getUpdateCount();
			System.out.println("update 수 : " + re);
			if(re >= 1) {
				System.out.println("작업 성공");
			} else {
				System.out.println("작업 실패");
			}
			
			b = stmt.execute("select * from sangdata"); // select이기 때문에 true를 반환.
			System.out.println("select 후 : " + b);
			rs = stmt.getResultSet(); // b에서 실행 시킨 stmt.execute 값을 getResultSet()으로 받음.
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println("process err :" + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
	
	public static void main(String[] args) {
		new DbTest6Execute();

	}
	
}
