package ex7;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class DbTable extends JFrame implements ActionListener {
	
	String[][] datas = new String[0][4];
	String[] title = {"부서번호","부서명","부서전화","부서위치"}; // 테이블 타이틀 설정.
	DefaultTableModel model;
	JTable table;
	JTextArea txtJG;
	
	Connection conn;
	PreparedStatement pstmt, pstmt2; // pstmt, rs 두 개 설정. 열린 상태에서 두 개를 하나에 사용 할 수 없음.
	ResultSet rs, rs2;

	public DbTable() {
		layInit(); 
		accDb();
		
		setBounds(200, 100, 300, 600);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(DbTable.this,
						"정말 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
				if (re == JOptionPane.OK_OPTION) {
					try {
						if (rs != null) rs.close();
						if (pstmt != null) pstmt.close();
						if (rs2 != null) rs2.close();
						if (pstmt2 != null) pstmt2.close();
						if (conn != null) conn.close();
						System.exit(0);
					} catch (Exception e2) {
					}
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent(); // 마우스 클릭 대상.
				model = (DefaultTableModel)table.getModel(); // 값은 모델에 저장되어 있음.
				
				select();
			}
		});

	}
	
	// 레이아웃
	private void layInit() {
		
		model = new DefaultTableModel(datas, title);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500,100)); // 테이블 크기 설정.
		table.getColumnModel().getColumn(0).setPreferredWidth(60); // 테이블 열 설정.
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		JScrollPane pane = new JScrollPane(table);
		add("North", pane);
		
		txtJG = new JTextArea(5,5); // 고객 자료 출력 TxTArea.
		JScrollPane pane2 = new JScrollPane(txtJG);
		
		add("Center", pane2);
		
	}
	
	// buser 연결 및 테이블 생성.
	private void accDb( ) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// 부서 테이블 출력 SQL
			pstmt = conn.prepareStatement("select * from buser order by buser_no asc");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("buser_no");
				String name = rs.getString("buser_name");
				String loc = rs.getString("buser_tel");
				String tel = rs.getString("buser_loc");
				String []imsi = {no, name, loc, tel};
				model.addRow(imsi);
			}
		} catch (Exception e) {
			System.out.println("DB err : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	// 직원 및 고객 설정
	private void select() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// 부서명에 대한 직원 출력 SQL.
			String ssql = "select buser_num, jikwon_name " + 
						  "from jikwon right join buser on buser_num=buser_no " +
						  "where buser_name=?";
			pstmt = conn.prepareStatement(ssql);
			// 마우스 클릭시 해당 행의 부서명을 받아 옴.
			String buserName = (String)model.getValueAt(table.getSelectedRow(), 1);
			pstmt.setString(1, buserName);
			rs = pstmt.executeQuery();
			
			txtJG.setText(null);
			// 직원 이름 출력.
			while(rs.next()) {
				txtJG.append("직원이름 : ");
				String str = rs.getString("jikwon_name") + "\n";
				txtJG.append(str);
				
				try
				{
					// 직원에 대한 고객 출력 SQL.
					String gsql = "select gogek_name, gogek_tel, gogek_jumin " + 
								  "from jikwon inner join gogek on jikwon_no=gogek_damsano " + 
								  "where jikwon_name=?";
					// 현재 while 돌 때에 직원 이름을 불러옴.
					String jikwonName = rs.getString("jikwon_name");
					pstmt2 = conn.prepareStatement(gsql);
					pstmt2.setString(1, jikwonName);
					rs2 = pstmt2.executeQuery();
					
					if(rs2.next()) {
						txtJG.append("관리고객은 : \n");
						txtJG.append("---------------------------------------------------------------------\n");
						txtJG.append("고객명 \t" + "고객전화 \t" + "고객주민 \n");
						txtJG.append("---------------------------------------------------------------------\n");
						do{
							String str2 = rs2.getString("gogek_name") + "\t" + 
									rs2.getString("gogek_tel") + "\t"+ 
									rs2.getString("gogek_jumin") + "\n";
							txtJG.append(str2);
						} while(rs2.next());
						txtJG.append("---------------------------------------------------------------------\n");
					} else {
						txtJG.append("---------------------------------------------------------------------\n");
						txtJG.append("관리고객 없음 \n");
						txtJG.append("---------------------------------------------------------------------\n");
					}
				} catch(Exception e) {
					System.out.println("DB gogekSelect : " + e);
				}
				txtJG.append("\n");
			}
		} catch (Exception e) {
			System.out.println("DB buserSelect : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	/*
	private void gogekSelect() {
		try {
			String ssql = "select gogek_name, gogek_tel, gogek_jumin " + 
						  "from jikwon left join gogek on jikwon_no=gogek_damsano " + 
						  "where jikwon_name=?";
			pstmt = conn.prepareStatement(ssql);
			String jikwonName = rs.getString("jikwon_name");
			pstmt.setString(1, jikwonName);
			rs = pstmt.executeQuery();
			txtJG.append("관리고객은 : \n");
			txtJG.append("고객명 \t" + "고객전화 \t" + "고객주민 \n");
			while(rs.next()) {
				
				String str = rs.getString("gogek_name") + "\t" +
							 rs.getString("gogek_tel") + "\t" +
							 rs.getString("gogek_jumin") + "\n";
				txtJG.append(str);
			}
		} catch (Exception e) {
			System.out.println("DB gogekSelect : " + e);
		} finally {
//			try {
//				if (rs != null) rs.close();
//				if (pstmt != null) pstmt.close();
//				if (conn != null) conn.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
		}
	}
	*/
	@Override
	public void actionPerformed(ActionEvent e) { }
	
	public static void main(String[] args) {
		new DbTable();
	}
}
