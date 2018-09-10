package my.shop.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import my.shop.board.BoardBean;

public class MemberManager {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public MemberManager( ) {
		try {
			Context context = new InitialContext();
			// dbcp(DB Context Pooling). 폴링처리
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("BoardManager err : " + e);
		}
	}
	
	public ArrayList<ZipcodeBean> zipRead(String p_area3) { // 우편번호 찾기
		ArrayList<ZipcodeBean> list = new ArrayList<>();
		try {
			conn = ds.getConnection();
			String sql = "select * from ziptab where area3 like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_area3 + "%"); // 시작되는 글자로 찾음
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString("zipcode"));
				ZipcodeBean bean = new ZipcodeBean();
				bean.setZipcode(rs.getString("zipcode"));
				bean.setArea1(rs.getString("area1"));
				bean.setArea2(rs.getString("area2"));
				bean.setArea3(rs.getString("area3"));
				bean.setArea4(rs.getString("area4"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("zipRead err : " + e);
		}  finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return list;
	}

	public Boolean checkId(String id) { // 우편번호 찾기
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "select id from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // 시작되는 글자로 찾음
			rs = pstmt.executeQuery();
			b = rs.next(); // 있으면 true, 없으면 false	
		} catch (Exception e) {
			System.out.println("checkId err : " + e);
		}  finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return b;
	}
	
	public boolean memInsert(MemberBean bean) { // 회원 Insert
		boolean b = false;
		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPasswd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getZipcode());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getJob());
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("memInsert err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return b;
	}
	
	public boolean loginCheck(String id, String passwd) { // 회원 Insert
		boolean b = false;
		
		String sql = "select id, passwd from member where id=? and passwd=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("loginCheck err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return b;
	}
	
	public MemberBean getMember(String id) { // 수정할 자료 Road
		MemberBean bean = null;
		String sql = "select * from member where id=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPasswd(rs.getString("passwd"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setAddress(rs.getString("address"));
				bean.setJob(rs.getString("job"));
			}
		} catch (Exception e) {
			System.out.println("getMember err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return bean;
	}
	
	public boolean memberUpdate(MemberBean bean, String id) { // 회원정보 Update
		boolean b = false;
		String sql = "update member set passwd=?, name=?, email=?, phone=?, zipcode=?, address=?, job=? where id=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getPasswd());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhone());
			pstmt.setString(5, bean.getZipcode());
			pstmt.setString(6, bean.getAddress());
			pstmt.setString(7, bean.getJob());
			pstmt.setString(8, id);

			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("memberUpdate err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return b;
	}
	
	public boolean adminLoginChk(String adminid, String adminpasswd) { // 회원정보 Update
		boolean b = false;
		String sql = "select * from admin where admin_id=? and admin_passwd=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminid);
			pstmt.setString(2, adminpasswd);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("adminLoginChk err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return b;
	}
	
	public ArrayList<MemberBean> getmemberAll() {
		ArrayList<MemberBean> list = new ArrayList<>();
		String sql = "select * from member";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setPasswd(rs.getString("passwd"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getmemberAll err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
}
