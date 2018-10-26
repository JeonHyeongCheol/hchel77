package pack.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.DataSource;


@Controller
@RequestMapping("list")
public class ListController {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map getJsonlist() {
		
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
	
		try {
			String sql = "select * from sangdata";
			conn = dataSource.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String, String> data = new HashMap<String, String>();
				data.put("code", rs.getString("code"));
				data.put("sang", rs.getString("sang"));
				data.put("su", rs.getString("su"));
				data.put("dan", rs.getString("dan"));
				dataList.add(data);
			}
		} catch (Exception e) {
			System.out.println("getJsonlist Err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("datas", dataList);
		return data2;
		
	}
		
}