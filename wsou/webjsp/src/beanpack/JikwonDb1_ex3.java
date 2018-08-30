package beanpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JikwonDb1_ex3 {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String buser;
	private int cou, max, min, sum, avg;
	public void setBuser(String buser) {
		this.buser = buser;
	}
	
	public String getBuser() {
		return buser;
	}
	
	public JikwonDb1_ex3() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	public ArrayList<JikwonDto_ex3> getDatas() {
		ArrayList<JikwonDto_ex3> jiklist = new ArrayList<>();
		
		try {
			String url="jdbc:mysql://127.0.0.1:3306/test"; 
			conn = DriverManager.getConnection(url,"root","123");
			
			String sql = "select jikwon_no, jikwon_name, jikwon_jik, jikwon_gen, jikwon_pay from jikwon inner join buser on buser_num=buser_no where buser_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buser);
			rs = pstmt.executeQuery();
			
			rs.last();
			int cou = rs.getRow();
			rs.beforeFirst();
			
			avg = 0;
			max = 0;
			min = Integer.MAX_VALUE;
			sum = 0;

			while(rs.next()) {
				
				
				JikwonDto_ex3 dto = new JikwonDto_ex3();
				dto.setJikwon_no(rs.getInt(1));
				dto.setJikwon_name(rs.getString(2));
				dto.setJikwon_jik(rs.getString(3));
				dto.setJikwon_gen(rs.getString(4));
				jiklist.add(dto);
				
				if(rs.getInt(5) > max) {
					max = rs.getInt(5);
				}
				
				if(rs.getInt(5) < min) {
					min = rs.getInt(5);
				}
				
				sum += rs.getInt(5);
		
			}
			
			avg = sum/cou; 
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
	
	public int getMax() {
		return max;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getAvg() {
		return avg;
	}
}
