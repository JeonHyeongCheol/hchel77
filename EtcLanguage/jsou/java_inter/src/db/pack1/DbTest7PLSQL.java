package db.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

// SQL 테이블 생성 후 클래스 작업 실행
/*
SQL> create table myjik as select jikwon_no, jikwon_name,
2  jikwon_pay from jikwon;

테이블이 생성되었습니다.

SQL>
SQL>
SQL> create or replace procedure pro_del(no myjik.jikwon_no%TYPE) is
2  begin delete from myjik where jikwon_no = no;
3  end;
4  /

프로시저가 생성되었습니다.
*/
public class DbTest7PLSQL {

	Connection conn;
	CallableStatement cstmt; // procedure 처리용
	
	public DbTest7PLSQL() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			process();
		} catch (Exception e) {
			System.out.println("DbTest7PLSQL err : " + e);
		}
	}
	
	private void process() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			cstmt = conn.prepareCall("{call pro_del(?)}"); // 프로시저 부르는 명령어. 여러개 할 경우 "{call pro_del(?,?,?)}" 이런식으로 하면 됨. 
			cstmt.setInt(1, 10); // ? 물음표 순서에 값을 넣어줌. 물음표는 여러 개 할 수 있음.
			int re = cstmt.executeUpdate();
			if(re >= 1) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패"); 
			}
		} catch (Exception e) {
			System.out.println("process err : " + e);
		} finally {
			try {
				if(cstmt != null) cstmt.close(); // 같지 않음 연산자(!=)는 피연산자가 같으면 false, 다르면 true를 반환합니다.
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public static void main(String[] args) {
		new DbTest7PLSQL();
	}

}
