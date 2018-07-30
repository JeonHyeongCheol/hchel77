package db.pack1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextArea;

public class DbTest5Pooling extends JTextArea implements ActionListener{

	Connection conn;
	Statement stmt;
	ResultSet rs;
	DBConnectionMgr pool;
	
	public DbTest5Pooling() {
		try {
			pool = DBConnectionMgr.getInstance();
			accDb();
		} catch (Exception e) {
			System.out.println("연결실패 : " + e);
		}
	}
	
	private void accDb() {
		try {
			conn = pool.getConnection();
			String sql = "select * from sangdata";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(
				rs.getString(1) + " " +
				rs.getString(2) + " " +
				rs.getString(3) + " " +
				rs.getString(4) + " " );
			}
		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new DbTest5Pooling();
	}
}
