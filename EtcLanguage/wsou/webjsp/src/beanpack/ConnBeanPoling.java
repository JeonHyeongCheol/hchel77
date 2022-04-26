package beanpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnBeanPoling {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ConnBeanPoling() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria"); 
			// java:comp/env/까지 Keyword, 뒤에는 context name명 쓰면됨.
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e);
		}
	}
	
	public ArrayList<SangpumDto> getDatas() {
		ArrayList<SangpumDto> list = new ArrayList<>(); // DB값 저장 위해 ArrayList 컬렉션 생성
		try {
			conn = ds.getConnection(); // 내부적으로 풀링처리를 하면서 DB에 연결시켜줌
			
			String sql = "select code, sang, su, dan from sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SangpumDto dto = new SangpumDto(); // SangpumDto에 값을 저장 후 Arraylist에 넘김
				dto.setCode(rs.getString(1));
				dto.setSang(rs.getString(2));
				dto.setSu(rs.getInt(3));
				dto.setDan(rs.getInt(4));
				list.add(dto); // Arraylist에 넣음
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
	
	public SangpumDto updateList(String code) {
		SangpumDto dto = null;
		//System.out.println("code : " + code );
		try {
			conn = ds.getConnection(); // 내부적으로 풀링처리를 하면서 DB에 연결시켜줌
			String sql = "select * from sangdata where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new SangpumDto();
				dto.setCode(rs.getString(1));
				dto.setSang(rs.getString(2));
				dto.setSu(rs.getInt(3));
				dto.setDan(rs.getInt(4));
			}
		} catch (Exception e) {
			System.out.println("updateList err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// 
			}
		}
		return dto;
	}
	
	public boolean updateData(SangpumForm form) {
		boolean b = false; // 성공하면 true, 실패하면 false 반환
		//System.out.println("code : " + code );
		try {
			conn = ds.getConnection(); // 내부적으로 풀링처리를 하면서 DB에 연결시켜줌
			String sql = "update sangdata set sang=?,su=?,dan=? where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, form.getSang());
			pstmt.setInt(2, form.getSu());
			pstmt.setInt(3, form.getDan());
			pstmt.setString(4, form.getCode());
			if(pstmt.executeUpdate() > 0) b = true;

		} catch (Exception e) {
			System.out.println("updateData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// 
			}
		}
		return b;
	}
	
	public boolean delData(String code) {
		boolean b = false; // 성공하면 true, 실패하면 false 반환
		//System.out.println("code : " + code );
		try {
			conn = ds.getConnection(); // 내부적으로 풀링처리를 하면서 DB에 연결시켜줌
			String sql = "delete from sangdata where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("updateData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// 
			}
		}
		return b;
	}
}
