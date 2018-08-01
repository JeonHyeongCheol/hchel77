package db.pack1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ex6.DbPstmt;

public class DbTest13CRD extends JFrame implements ActionListener{
	
	private JButton btnInsert, btnDelete, btnExit;
	private String [][] datas = new String[0][4];
	private String[] title = {"코드","상품명","수량","단가"};
	private DefaultTableModel model = new DefaultTableModel(datas, title);
	private JTable table = new JTable(model);
	private JLabel lblCou = new JLabel("건수:0");
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DbTest13CRD() {
		btnInsert = new JButton("추가");
		btnDelete = new JButton("삭제");
		btnExit = new JButton("종료");
		btnInsert.addActionListener(this);
		btnDelete.addActionListener(this);
		btnExit.addActionListener(this);
		JPanel panel = new JPanel();
		panel.add(btnInsert);
		panel.add(btnDelete);
		panel.add(btnExit);
		add("North", panel);
		
		// table의 열폭 조정.
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		
		JScrollPane pane = new JScrollPane(table);
		add("Center", pane);
		add("South", lblCou);
		
		setTitle("상품처리");
		setResizable(false);
		setBounds(200, 200, 300, 250);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(DbTest13CRD.this, "정말 종료 하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
				if(re == JOptionPane.OK_OPTION) {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
		accDb();
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("accDb err : " + e.getMessage());
			return;
		}
		
		dispData();
	}
	
	private void dispData() {
		model.setNumRows(0); // table의 초기화.
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			String sql = "select * from sangdata order by code desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int cou = 0;
			while(rs.next()) {
				String[]imsi = {
						rs.getString("code"),
						rs.getString("sang"),
						rs.getString("su"),
						rs.getString("dan"),
				};
				model.addRow(imsi);
				cou++;
			}
			lblCou.setText("건수:" + cou);
		} catch (Exception e) {
			System.out.println("dispData err : " + e);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) { // 상품 추가
			InsertForm insertForm = new InsertForm(this);
			dispData(); // 추가 후 자료 다시 읽기
		} else if(e.getSource() == btnDelete) { // 상품 삭제
			String delNo;
			while(true) {
				delNo = JOptionPane.showInputDialog(this,"삭제할 코드 입력");
				if(delNo!=null && delNo.equals("") ) {
					JOptionPane.showMessageDialog(this, "삭제할 코드를 입력하세요.");
				}else if(delNo==null) {
					return;
				}else if(delNo!=null){
					break;
				}
			}
			
			// String delNo = JOptionPane.showInputDialog(this, "삭제할 코드 입력");
			int res = JOptionPane.showConfirmDialog(this, "정말로 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_CANCEL_OPTION);
			if(res == JOptionPane.YES_OPTION) {
				try {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
					// 삭제 여부 묻기 필요 : 생략
					String sql = "delete from sangdata where code=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, delNo);
					if (pstmt.executeUpdate() == 0) {
						JOptionPane.showMessageDialog(this, "delNo" + "등록된 코드가 아닙니다");
						return;
					} else {
						JOptionPane.showMessageDialog(this, "삭제 성공");
						dispData();
					}
				} catch (Exception e2) {
					// TODO: handle exception
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
			
		} else if(e.getSource() == btnExit) { // 종료
			int re = JOptionPane.showConfirmDialog(DbTest13CRD.this, "정말로 종료 할까요?", "종료", JOptionPane.OK_CANCEL_OPTION);
			if(re == JOptionPane.OK_OPTION) {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();// == System.exit(0);
				} catch (Exception e2) {
					System.out.println("DB Close Err : " + e2.getMessage());
				}
			} else {
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		}
	}
	
	// 자료 추가용 내부 클래스 - 대화상자
	class InsertForm extends JDialog implements ActionListener {
		JTextField txtSang = new JTextField();
		JTextField txtSu = new JTextField();
		JTextField txtDan = new JTextField();
		JButton btnOk = new JButton("등록");
		JButton btnCancel = new JButton("지움");
		
		public InsertForm(JFrame frame) {
			super(frame, "상품추가");
			setModal(true); // Modal Dialog
			
			// 추가 화면 디자인
			JPanel pn1 = new JPanel(new GridLayout(4, 2));
			pn1.add(new JLabel("품명:")); // 1행
			pn1.add(txtSang);
			pn1.add(new JLabel("수량:")); // 1행
			pn1.add(txtSu);
			pn1.add(new JLabel("단가:")); // 1행
			pn1.add(txtDan);
			pn1.add(btnOk); // 4행
			pn1.add(btnCancel);
			btnOk.addActionListener(this);
			btnCancel.addActionListener(this);
			
			add("North", new JLabel("*자료입력*"));
			add("Center", pn1);
			
			setBounds(210, 210, 150, 150);
			setVisible(true);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//dispose();
				}
			});
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOk) { // 새 상품 추가 작업
				
				if(txtSang.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "상품명을 입력하시오"); 
					txtSang.requestFocus(); 
					return;
				}
				
				if(txtSu.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "수량을 입력하시오"); 
					txtSu.requestFocus(); 
					return;
				}
//				
				if(txtDan.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "단가를 입력하시오"); 
					txtDan.requestFocus(); 
					return;
				}
				
				try {
					Integer.parseInt(txtSu.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "수량은 숫자만 허용");
					txtSu.requestFocus();
					return;
				}
				
				try {
					Integer.parseInt(txtDan.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "단가는 숫자만 허용");
					txtDan.requestFocus();
					return;
				}
				
				// 등록 가능한 상태
				try {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
					
					// 새 상품 코드 구하기
					int new_code = 0;
					String sql = "select max(code) from sangdata";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						new_code = rs.getInt(1);
					}
					//System.out.println(new_code + 1);
					
					sql = "insert into sangdata values(?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, new_code + 1);
					pstmt.setString(2, txtSang.getText().trim());
					pstmt.setString(3, txtSu.getText().trim()); // trim() : 앞뒤 공백 제거
					pstmt.setString(4, txtDan.getText().trim());
					if(pstmt.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(this, "등록 성공");
					} else {
						JOptionPane.showMessageDialog(this, "등록 실패");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				} finally {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			} else { // 입력자료 화면 지우기
				txtSang.setText(null);
				txtSu.setText(null);
				txtDan.setText(null);
				txtSang.requestFocus();
			}
		} 
	}
	

	
	public static void main(String[] args) {
		new DbTest13CRD();
	}
}
