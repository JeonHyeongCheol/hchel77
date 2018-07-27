package db.pack1;
// Java - JDBC 연동 : 프로젝트만들 때 libraries에 ojdbc.jar를 추가. 
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DbTest1 {
	private Connection conn; // DB 연결 객체
	private Statement stmt; // SQL문 실행
	private ResultSet rs; // select문의 결과 접근
	
	public DbTest1() {
		try {
			// 1. Driver 클래스 로딩 - 램으로 끌어 올리는 것.
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Libraries에 있는 클래스 로딩.
		} catch (Exception e) {
			System.out.println("Driver 로딩 실패" + e);
			return; // 클래스에서 빠짐.
		}
		
		try {
			// 2. DB와 연결.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger"); 
			// url는 127.0.0.1 아니면 localhost를 쓰면 됨. 
			// 다른 컴퓨터를 들어 갈 때는 포트를 타고 가는데 포트번호를 사용. DB에서는 1521 사용.
		} catch (Exception e) {
			System.out.println("DB 연걸 실패" + e);
			return;
		}
		
		try {
			// 2. SQL문을 사용해 자료 읽기.
			// 한 줄만 읽기.
			stmt = conn.createStatement();
			/*
			rs = stmt.executeQuery("select * from sangdata");
			// url는 127.0.0.1 아니면 localhost를 쓰면 됨. 
			// 다른 컴퓨터를 들어 갈 때는 포트를 타고 가는데 포트번호를 사용. DB에서는 1521 사용.
			rs.next(); // Record pointer 이동
			String code = rs.getString("code"); // 칼럼명 = 인덱스
			String sangpum = rs.getString("sang");
			int su = rs.getInt("su"); // 연산할 때만 int로 사용. 거의 String으로 함.
			int dan = rs.getInt("dan");
			System.out.println(code + " " + sangpum + " " + su + " " + dan);
			*/
			
			// 모든 자료 읽기
			//String sql = "select code,sang,su as 수량,dan as 단가 from sangdata";
			String sql = "select code,sang,su as 수량,dan as 단가 from sangdata";
			// 별명 사용시 밑에서 원본 칼럼명을 쓰면 나오지 않음. 별명을 써줘야지 나옴. client는 원본칼럼명을 알지 못함.
			rs = stmt.executeQuery(sql);
			int count = 0;
			while(rs.next()) { // rs.next는 있는지 없는지 알기위해서 사용하는 것이기 때문에 == ture라고 쓸 필요 없음.
				String code = rs.getString(1);
				String sangpum = rs.getString(2);
				String su = rs.getString("수량");
				String dan = rs.getString("단가");
				System.out.println(code + " " + sangpum + " " + su + " " + dan);
				count += 1; // 건 수 구하기 2
			}
			// 건 수 구하기
			sql = "select count(*) from sangdata";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println("건수 : " + rs.getInt(1)); // DB에 접속하지 않고 프로그램상에서 카운터를 올릴 때. 변수명.getint(1)을 쓰면 됨.
			System.out.println("건수 : " + count);

		} catch (Exception e) {
			System.out.println("처리 실패" + e);
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
		new DbTest1();
	}
}
