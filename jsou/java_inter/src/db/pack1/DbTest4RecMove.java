package db.pack1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

	// Scrollable ResultSet을 사용하면 다음과 같은 method가 사용가능하다.
	//
	// 1) ResultSet.next() : Cursor를 다음 Row로 이동
	// 2) ResultSet.previous() : Cursor를 이전 Row로 이동
	// 3) ResultSet.first() : Cursor를 ResulSet의 처음으로 이동
	// 4) ResultSet.last() : Cursor를 ResulSet의 마지막으로 이동
	// 5) ResultSet.isFirst() : 현재 Cursor가 첫 Row인지를 확인
	// 6) ResultSet.isLast() : 현재 Cursor가 마지막 Row인지를 확인
	// 7) ResultSet.getType() : ResultSet의 Type을 Return (Type은 아래 참조 (A))
	// 8) ResultSet.getCucurrency() : Concurrency의 Type을 Return (Type은 아래 참조 (B))
	// 9) ResultSet.getRow() : 현재 Cursor가 가리키고 있는 Row Number
	//
	// ResultSet의 Type과 Concurrency의 Type은 Statement 생성시에 선언해 주며, 각각의 Type은 다음과 같다.
	//
	// * ResultSet / Concurrency Type 선언
	// Statement stmt = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,
	// ResultSet.CONCUR_UPDATABLE);
	//
	// * ReusltSet의 Type (A)
	// 1) TYPE_FORWARD_ONLY : scroll이 불가능한 forwad only 형
	// 2) TYPE_SCROLL_INSENSITIVE : scroll은 가능하나, 변경된 사항은 적용되지 않음
	// 3) TYPE_SCROLL_SENSITIVE : scroll은 가능하며, 변경된 사항이 적용됨
	//
	// * CONCURRENCY의 TYPE (B)
	// 1) CONCUR_READ_ONLY : resultset object의 변경이 불가능
	// 2) CONCUR_UPDATABLE : resultset object의 변경이 가능


public class DbTest4RecMove extends JFrame implements ActionListener{

	JButton btnF, btnP, btnN, btnL; // 레코드 이동방향 버튼
	JTextField txtNo, txtName, txtJik, txtBName, txtBTel;
	JTextArea txtGogek;
	
	
	Connection conn;
	Statement stmt, stmt2;
	ResultSet rs, rs2;
	// JButton, JTextField, Connection, Statement, ResultSet는 여기서 포함 관계, JFrame은 상속 관계
	
	public DbTest4RecMove() {
		setTitle("레코드 이동 연습");
		
		layInit();
		accDb();
		
		setBounds(500, 500, 500, 300);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(DbTest4RecMove.this, "정말로 종료 할까요?", "종료", JOptionPane.YES_NO_OPTION);
				if(re == JOptionPane.YES_OPTION) {
					try {
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(rs2 != null) rs.close();
						if(stmt2 != null) stmt.close();
						if(conn != null) conn.close();						
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					} catch (Exception e2) {
						System.out.println("DB Close Err : " + e2.getMessage());
					}
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
				
			}
		});
	}
	
	private void layInit() {
		
		
//		setLayout(new GridLayout(4, 1));
		
		setLayout(new FlowLayout());
		
		txtNo = new JTextField("", 5);
		txtName = new JTextField("", 10);
		txtJik = new JTextField("", 10);
		txtBName = new JTextField("", 10);
		txtBTel = new JTextField("", 10);
		
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("사번:"));
		panel1.add(txtNo);
		panel1.add(new JLabel("직원이름:"));
		panel1.add(txtName);
		panel1.add(new JLabel("직급:"));
		panel1.add(txtJik);
		add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("부서명:"));
		panel2.add(txtBName);
		panel2.add(new JLabel("부서전화:"));
		panel2.add(txtBTel);
		add(panel2);
		
		btnF = new JButton("|<<");
		btnP = new JButton("<");
		btnN = new JButton(">");
		btnL = new JButton(">>|");
		JPanel panel3 = new JPanel();
		panel3.add(btnF);
		panel3.add(btnP);
		panel3.add(btnN);
		panel3.add(btnL);
		add(panel3);
		
		txtGogek = new JTextArea(8,40);
		JScrollPane pane = new JScrollPane(txtGogek);
		JPanel panel4 = new JPanel();
		panel4.add(pane);
		add("North", pane);
		
		
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);

	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			//stmt = conn.createStatement(); // rs.next()  가능 : 레코드 방향 순방향.
			
			// 레코드 방향 역방향 설정
		
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, // Scroll은 가능하나, 변경된 사항은 적용되지 않음.
										ResultSet.CONCUR_READ_ONLY); // ResulTset Object의 변경이 불가능.
			
			rs = stmt.executeQuery("select jikwon_no, jikwon_name, jikwon_jik, buser_name, buser_tel "
					+ "from jikwon "
					+ "left join buser "
					+ "on buser_num = buser_no "
					+ "order by jikwon_no");
			rs.next();
			
			display();
		} catch (Exception e) {
			System.out.println("accDb err: " + e); // 프로그래머에게 보여줘야하는 메세지.
		}
	}
	
	private void displaygogek() {
		
		try {
			txtGogek.setText(null);
			stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, // Scroll은 가능하나, 변경된 사항은 적용되지 않음.
					ResultSet.CONCUR_READ_ONLY);
			
			rs2 = stmt2.executeQuery("select gogek_no, gogek_name, "
					+ " decode(substr(gogek_jumin,8,1),1, '남', 2, '여') as 고객성별,"
					+ " trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) as 고객나이" 
					+ " from jikwon inner join gogek"
					+ " on gogek_damsano = jikwon_no"
					+ " where jikwon_no =" + rs.getString("jikwon_no"));
			
			int cou = 0;
			txtGogek.append("고객번호" + "\t" + "고객이름" + "\t" + "고객성별" + "\t" + "고객나이" + "\n");
			while(rs2.next()) {
				String str = rs2.getString("gogek_no") + "\t" + 
							 rs2.getString("gogek_name") + "\t" +
							 rs2.getString("고객성별") + "\t" +
							 rs2.getString("고객나이") + "\n";
				txtGogek.append(str);
				cou++;
			}
			
			txtGogek.append("인원수 : " + cou + "명");

		} catch(Exception e2) {
			System.out.println("DB Gogek err : " + e2);
		}
	}
	
	private void display() {
		try {
			txtNo.setText(rs.getString("jikwon_no"));
			txtName.setText(rs.getString("jikwon_name"));
			txtJik.setText(rs.getString("jikwon_jik"));		
			txtBName.setText(rs.getString("buser_name"));		
			txtBTel.setText(rs.getString("buser_tel"));
			displaygogek();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "자료의 처음 또는 마지막입니다"); // 사용자에게 보여줘야하는 메세지.
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == btnF) rs.first(); // 맨처음
			else if(e.getSource() == btnP) rs.previous(); // 이전
			else if(e.getSource() == btnN) rs.next(); // 다음
			else if(e.getSource() == btnL) rs.last(); // 맨마지막
			
			display();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}
	
	public static void main(String[] args) {
		new DbTest4RecMove();

	}

}
