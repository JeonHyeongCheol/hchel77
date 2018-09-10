package my.shop.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import my.shop.member.ZipcodeBean;

public class ProductManager {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ProductManager() {
		try {
			Context context = new InitialContext();
			
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("BoardManager err : " + e);
		}
	}
	
	public ArrayList<ProductBean> getProductAll() {
		ArrayList<ProductBean> list = new ArrayList<>();
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getString("price"));
				bean.setDetail(rs.getString("detail"));
				bean.setSdate(rs.getString("sdate"));
				bean.setStock(rs.getString("stock"));
				bean.setImage(rs.getString("image"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getProductAll err : " + e);
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
	
	public boolean insertProduct(HttpServletRequest request) { 
		// 예전에 했던 방식은 bean을 사용했지만 request로 모든 값을 받아서 사용 할 수 있음.
		// request로 변수를 받음. httpServletRequest Interface 사용.
		boolean b = false;
		try {
			// multipart 사용.
			// 상대경로 안됨. 절대경로를 주어야 함.
			// 업로드 할 이미지 절대경로
			String uploadDir = "C:/work/wsou/myshop/WebContent/data";
			MultipartRequest multi = new MultipartRequest
					(request, uploadDir, 1024 * 1024 * 5, "utf-8", new DefaultFileRenamePolicy()); // 이미지가 오게되면 이미지를 uploadDir에 끌어 올림. 
			conn = ds.getConnection();
			String sql = "insert into shop_product (name,price,detail,sdate,stock,image) values(?,?,?,now(),?,?)"; // now는 현재 날짜.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("name")); // multpart로 사용하면 예전에 받았던 방식 처럼 변수명.getParameter 사용 하면 됨.
			pstmt.setString(2, multi.getParameter("price"));
			pstmt.setString(3, multi.getParameter("detail"));
			pstmt.setString(4, multi.getParameter("stock"));
			if(multi.getFilesystemName("image") == null) { // 들어오는 이미지가 없으면 ready.gif를 넣어줌.
				pstmt.setString(5, "ready.gif");
			} else {
				pstmt.setString(5, multi.getFilesystemName("image")); // 이미지를 받을 때는 getFilesystemname으로 받아야 함.
			}
			if(pstmt.executeUpdate() > 0) b=true;
		} catch (Exception e) {
			System.out.println("insertProduct err : " + e);
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
	
	public ProductBean getProduct(String no) {
		ProductBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new ProductBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getString("price"));
				bean.setDetail(rs.getString("detail"));
				bean.setSdate(rs.getString("sdate"));
				bean.setStock(rs.getString("stock"));
				bean.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			System.out.println("getProduct err : " + e);
		}  finally {
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
	
	public boolean updateProduct(HttpServletRequest request) { 
		boolean b = false;
		try { 
			String uploadDir = "C:/work/wsou/myshop/WebContent/data";
			MultipartRequest multi = new MultipartRequest
					(request, uploadDir, 1024 * 1024 * 5, "utf-8", new DefaultFileRenamePolicy());
			conn = ds.getConnection();
			String sql = "update shop_product set name=?,price=?,detail=?,stock=?,image=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("name")); 
			pstmt.setString(2, multi.getParameter("price"));
			pstmt.setString(3, multi.getParameter("detail"));
			pstmt.setString(4, multi.getParameter("stock"));
			if(multi.getFilesystemName("image") == null) { 
				pstmt.setString(5, "ready.gif");
			} else {
				pstmt.setString(5, multi.getFilesystemName("image"));
			}
			pstmt.setString(6, multi.getParameter("no"));
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("updateProduct err : " + e);
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
	
	public boolean deleteProduct(HttpServletRequest request) { 
		boolean b = false;
		try { 
			conn = ds.getConnection();
			String sql = "delete from shop_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("deleteProduct err : " + e);
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
}
