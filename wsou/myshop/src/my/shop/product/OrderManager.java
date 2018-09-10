package my.shop.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderManager {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public OrderManager() {
		try {
			Context context = new InitialContext();
			
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("BoardManager err : " + e);
		}
	}
	
	public ArrayList<OrderBean> getOrderAll() {
		ArrayList<OrderBean> list = new ArrayList<>();
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_order";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
				bean.setId(rs.getString("id"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getOrderAll err : " + e);
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
}
