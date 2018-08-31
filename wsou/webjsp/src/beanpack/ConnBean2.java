package beanpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConnBean2 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private int recTotal = 0; // 전체 행 수
	private int pageSize = 5; // 각 페이지 당 출력 행 수
	private int totalPage = 0; // 전체 페이지 수
	
	public ConnBean2() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	public ArrayList<SangpumDto> getDatas(String pa) {
		ArrayList<SangpumDto> list = new ArrayList<>(); // DB값 저장 위해 ArrayList 컬렉션 생성
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			
			String sql = "select code, sang, su, dan from sangdata order by code desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int startNum = (Integer.parseInt(pa) - 1) * pageSize + 1; // rs.next(i) 를 구하기 위함. 
			for (int p = 1; p < startNum; p++) {
				rs.next(); // rec pointer 위치 : 0  5  10
			}
			int i = 1;
			while(rs.next() && i <= pageSize) { // pageSize 만큼 수행
				SangpumDto dto = new SangpumDto(); // SangpumDto에 값을 저장 후 Arraylist에 넘김
				dto.setCode(rs.getString(1));
				dto.setSang(rs.getString(2));
				dto.setSu(rs.getInt(3));
				dto.setDan(rs.getInt(4));
				list.add(dto); // Arraylist에 넣음
				i++; // 한 페이지 5개 출력하면 다음 페이지
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
	
	public int prepareTotalpage() {
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			
			String sql = "select count(*) from sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				recTotal = rs.getInt(1); // 전체 레코드 수
			}
			
			// 전체 페이지 수 얻기
			totalPage = recTotal / pageSize;
			if(recTotal % pageSize !=0 ) {//나머지가 0이 아니면
				totalPage += 1; // 자투리 계산(남아있는거)
			}
			
		} catch (Exception e) {
			System.out.println("prepareTotalpage err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// 
			}
		}
		return totalPage;
	}
	
	public void sangInsert(SangpumForm form) {
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			// 신상 코드 얻기
			pstmt = conn.prepareStatement("select max(code) from sangdata");
			rs = pstmt.executeQuery();
			rs.next();
			int maxCode = rs.getInt(1);
			
			// 신상 등록
			String insSql = "insert into sangData values(?,?,?,?)";
			pstmt = conn.prepareStatement(insSql);
			pstmt.setInt(1, maxCode + 1);
			pstmt.setString(2, form.getSang());
			pstmt.setInt(3, form.getSu());
			pstmt.setInt(4, form.getDan());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("sangInsert err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// 
			}
		}
	}
}
