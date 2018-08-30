package beanpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JikwonDb2_ex3 {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int cou, max, min, sum;
	private double avg;
	
	public JikwonDb2_ex3() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	public ArrayList<JikwonDto_ex3> getDatas(String buser) {
		ArrayList<JikwonDto_ex3> jiklist = new ArrayList<>();
		
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			
			String sql = "select jikwon_no, jikwon_name, jikwon_jik, jikwon_gen, jikwon_pay from jikwon inner join buser on buser_num=buser_no where buser_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buser);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				JikwonDto_ex3 dto = new JikwonDto_ex3();
				dto.setJikwon_no(rs.getInt(1));
				dto.setJikwon_name(rs.getString(2));
				dto.setJikwon_jik(rs.getString(3));
				dto.setJikwon_gen(rs.getString(4));
				jiklist.add(dto);
				
			}
			
	         sql = "select max(jikwon_pay),min(jikwon_pay),avg(jikwon_pay),count(*) from jikwon inner join buser on buser_no = buser_num where buser_name =?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, buser);
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	         max = rs.getInt(1);
	         min = rs.getInt(2);
	         avg = rs.getDouble(3);
	         cou = rs.getInt(4);

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

		return jiklist;
	}
	
	public String getEtcResult() {
		return "근무인원수 : "+ cou + ", 최대급여액 : "+ max + ", 최소급여액 : "+ min + ", 평균급여액 : "+ cou;
	}
	
}
