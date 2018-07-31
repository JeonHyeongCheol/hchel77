package db.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

// SQL 테이블 생성 후 클래스 작업 실행(직원넘버를 받고 그 이하에 자료만 출력 하는 것).
/*
SQL> create or replace procedure pro_sel2(cur2 out SYS_REFCURSOR,
  2  no in number) is
  3  begin open cur2 for select * from myjik where jikwon_no <= no;
  4  end;
  5  /

프로시저가 생성되었습니다.
*/


public class DbTest9PLSQL_SELECT2 {
	Connection conn;
	CallableStatement cstmt;
	OracleCallableStatement ocstmt; // Oracle에만(?)
	ResultSet rs;

	public DbTest9PLSQL_SELECT2() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pass = "tiger";
			conn = DriverManager.getConnection(url, user, pass);
			
			cstmt = conn.prepareCall("{call pro_sel2(?,?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setInt(2, 3);
			cstmt.execute();
			
			ocstmt = (OracleCallableStatement)cstmt;
			rs = ocstmt.getCursor(1); // output Cur가 물음표 첫번째에 있기 때문에 1이라고 써줘야 함.
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + 
								   rs.getString(2) + " " +
								   rs.getString(3) + " ");
			}
		} catch (Exception e) {
			System.out.println("err: " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(cstmt != null) cstmt.close();
				if(ocstmt != null) ocstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				
			}
		}

	}

	public static void main(String[] args) {
		new DbTest9PLSQL_SELECT2();
	}
}
