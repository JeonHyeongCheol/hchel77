package db.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

// SQL 테이블 생성 후 클래스 작업 실행
/*
  SQL> create or replace procedure pro_sel(cur1 out SYS_REFCURSOR) is
  2  begin
  3  open cur1 for select * from myjik;
  4  end;
  5  /

프로시저가 생성되었습니다.
*/


public class DbTest8PLSQL_SELECT {
	Connection conn;
	CallableStatement cstmt;
	OracleCallableStatement ocstmt; // Oracle에만(?)
	ResultSet rs;

	public DbTest8PLSQL_SELECT() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			cstmt = conn.prepareCall("{call pro_sel(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			
			ocstmt = (OracleCallableStatement)cstmt;
			rs = ocstmt.getCursor(1);
			
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
		new DbTest8PLSQL_SELECT();
	}
}
