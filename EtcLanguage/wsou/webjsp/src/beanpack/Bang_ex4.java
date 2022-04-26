package beanpack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Bang_ex4 {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public Bang_ex4() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria"); 
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e);
		}
	}
	
	public ArrayList<BangDto_ex4> getDatas() {
		ArrayList<BangDto_ex4> list = new ArrayList<>(); 
		try {
			conn = ds.getConnection(); 
			
			String sql = "select code, name, subject, content from guest";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BangDto_ex4 dto = new BangDto_ex4();
				dto.setCode(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setSubject(rs.getString(3));
				dto.setContent(rs.getString(4));
				list.add(dto);
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
	
	public boolean insertData(BangDto_ex4 dto) {
		boolean b = false;
		
		try {
			conn = ds.getConnection(); 
			
			// 신상 코드 얻기
			pstmt = conn.prepareStatement("select max(code) from guest");
			rs = pstmt.executeQuery();
			rs.next();
			int maxCode = rs.getInt(1);
			
			String sql = "insert into guest values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxCode + 1);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			if(pstmt.executeUpdate() > 0) b = true;			
			
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
		return b;
	}
	
	public BangDto_ex4 updateList(String code) {
		BangDto_ex4 dto = null;
		
		try {
			conn = ds.getConnection(); 
			String sql = "select * from guest where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BangDto_ex4();
				dto.setCode(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setSubject(rs.getString(3));
				dto.setContent(rs.getString(4));
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
	
	public boolean updateData(BangDto_ex4 dto) {
		boolean b = false; 
		
		try {
			conn = ds.getConnection(); 
			String sql = "update guest set name=?,subject=?,content=? where code=?";
			//System.out.println(form.getName() + " " + form.getSubject() + " " + form.getContent() + " ");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getCode());
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
		boolean b = false; 
		
		try {
			conn = ds.getConnection(); // 내부적으로 풀링처리를 하면서 DB에 연결시켜줌
			String sql = "delete from guest where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("delData err : " + e);
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
