package my.shop.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardManager {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds; // 폴링 처리
	
	private int tot; // 전체 레코드 수
	private int pList = 5; // 각 페이지 당 출력 레코드 수
	private int pageSu; // 전체 페이지 수
	
	public BoardManager() {
		try {
			Context context = new InitialContext();
			// dbcp(DB Context Pooling). 폴링처리
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("BoardManager err : " + e);
		}
	}
	
	public int currentGetMaxNum() { // 가장 큰 번호 얻기
		int cnt = 0;
		String sql = "select max(num) from shopboard"; // 게시판 테이블에 Max번호 가져오기.
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) cnt = rs.getInt(1); // Max 번호 가져오기
		} catch (Exception e) {
			System.out.println("currentGetMaxNum err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return cnt;
	}
	
	public void saveData(BoardBean bean) { // 게시판 Insert
		//System.out.println(bean.getName());
		String sql = "insert into shopboard values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); // readcnt 조회수 증가.
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, 0);
			pstmt.setInt(12, 0);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
	
	public ArrayList<BoardDto> getDataAll(int page, String stype, String sword) {
		ArrayList<BoardDto> list = new ArrayList<>();// 타입을 제한하기위해 제네릭 사용
		String sql = "select * from shopboard ";
		try {
			conn = ds.getConnection();
			
			if(sword == null) {
				sql += "order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
			} else { // 검색
				sql += "where " + stype + " like ?"; // stype : 이름별, 제목별 검색인지 넘어오는 값을 넣어줌.
				sql += "order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sword + "%"); // 넘어 오는 글자가 포함되어 있는 것을 검색.
			}
			
			rs = pstmt.executeQuery();
			
			
			for (int i = 0; i < (page - 1) * pList; i++) {
				rs.next(); // 0, 4, 9, 14 ... 레코드 포인터 위치
				// rs.next()에 마지막 값이 rs.next()로 넘어감.
			}
			
			int k = 0; // 레코드 수 증가 시키며 pList만큼 증가 시킴.
			
			while(rs.next() && k < pList) {
				BoardDto dto = new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("Name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);
				
				k++;
			}
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
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
	
	public void totalList() { // 전체 레코드 수 구하기
		String sql = "select count(*) from shopboard";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			tot = rs.getInt(1); // 전체 레코드 수
		} catch (Exception e) {
			System.out.println("totalList err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
	
	public int getPageSu() { // 전체 페이지 수 구하기
		pageSu = tot / pList;
		if(tot % pList > 0) pageSu++; // 짜투리 수 있으면 페이지값 증가
		return pageSu;
	}
	
	public BoardDto getData(String num) { // 게시판 상세내용
		String sql = "select * from shopboard where num=?";
		BoardDto dto = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDto();
				dto.setName(rs.getString("Name"));
				dto.setPass(rs.getString("pass"));
				dto.setMail(rs.getString("mail"));
				dto.setTitle(rs.getString("title"));
				dto.setCont(rs.getString("cont"));
				dto.setBip(rs.getString("bip"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
			}
		} catch (Exception e) {
			System.out.println("getData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
		
		return dto;
	}
	
	public void updateReadcnt(String num) { // 글 내용 보기 전에 조회 수 증가
		String sql = "update shopboard set readcnt=readcnt + 1 where num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateReadcnt err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
	}

	public BoardDto getReplyData(String num) { // 댓글용
		String sql = "select * from shopboard where num=?";
		BoardDto dto = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDto();
				dto.setTitle(rs.getString("title"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setOnum(rs.getInt("onum"));
				dto.setNested(rs.getInt("nested"));
			}
		} catch (Exception e) {
			System.out.println("getReplyData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
		
		return dto;
	} 

	public void updateOnum(int gnum, int onum) { // 댓글용 Number 수정
		// 같은 그룹의 레코드는 모두 작업에 참여
		// 같은 그룹의 onum 값 변경
		// 댓글의 onum은 이미 db에 있는 onum 보다 크거나 같은 값을 변경
		String sql="update shopboard set onum=onum + 1 where onum >= ? and gnum=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, gnum);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("updateOnum err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
	
	public void saveReplyData(BoardBean bean) { // 댓글 저장용
		String sql = "insert into shopboard values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); // readcnt 조회수 증가.
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, bean.getOnum());
			pstmt.setInt(12, bean.getNested());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveReplyData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
}

