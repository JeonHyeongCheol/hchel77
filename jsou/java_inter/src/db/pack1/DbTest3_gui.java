package db.pack1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DbTest3_gui extends JFrame implements ActionListener {
	// visual(비주얼)
	JButton btnAll = new JButton("전체");
	JButton btnM = new JButton("남");
	JButton btnF = new JButton("여");
	JTextArea txtResult = new JTextArea();
	
	// sql
	Connection conn;
	Statement stmt;
	ResultSet rs; 
	
	public DbTest3_gui() {
		setTitle("고객 테이블 출력");
		
		// 순서대로 실행
		layInit(); // 1. 레이아웃 잡고
		accDb(); // 2. DB 부르고
		
		// 3. 프레임 부르고
		setBounds(200, 200, 300, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit() {
		JPanel panel = new JPanel();
		panel.add(btnAll);
		panel.add(btnM);
		panel.add(btnF);
		
		txtResult.setEditable(false); // Read only(읽기만 가능, 쓰기 불가능).
		JScrollPane pane = new JScrollPane(txtResult); // 스크롤바
		
		add("Center", pane);
		add("North", panel);
		
		btnAll.addActionListener(this); // 버튼 누르면 ActionPerformed로 넘어감.
		btnM.addActionListener(this);
		btnF.addActionListener(this);

	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement();
			String sql = "select gogek_no, gogek_name, gogek_jumin from gogek";
			
			if(e.getSource() == btnAll) {
				
			} else if(e.getSource() == btnM) {
				sql += " where gogek_jumin like '%-1%'"; // 주민번호 -다음에 숫자가 1이면 // Sql에 Sql 문장을 더해서 하는 것.
				//sql += " where substr(gogek_jumin,8,1)=1";
			} else if(e.getSource() == btnF) {
				sql += " where gogek_jumin like '%-2%'";
			}
			
			txtResult.setText(null); // "" 요거랑 null 이랑 같음.
			
			rs = stmt.executeQuery(sql);
			int cou = 0;
			while(rs.next()) {
				//System.out.println(rs.getString(2)); // 2는 컬럼번호
				String str = rs.getString("gogek_no") + "\t" +
						rs.getString("gogek_name") + "\t" +
						rs.getString("gogek_jumin") + "\n";
				txtResult.append(str);
				cou++;
			}
			txtResult.append("인원수 : " + cou + "명");
			//System.out.println(sql);
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e3) {
				// TODO: handle exception
			}
			
		}
	}
	
	public static void main(String[] args) {
		new DbTest3_gui();

	}

}
