package kr.co.kic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class BookBannap extends JPanel implements ActionListener{
	JTextField txtBbun,txtBjemok,txtBdaeil,txtBdaebun, txtJemok;
	static JTextField txtBbanil;
	JButton btnBbun,btnChange,btnBannap,btnNew,btnClose;
	DefaultTableModel mod;
	
	static JFrame calFrame; 
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1, rs2;
	String sql;
	
	public BookBannap(){
		design();
		addListener();
		accDb();
	}
	
	private void addListener() {
		btnBbun.addActionListener(this);
		btnChange.addActionListener(this);
		btnBannap.addActionListener(this);
		btnNew.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			daebookDisplay();
		} catch (Exception e) {
			System.out.println("Bannap accDB err : " + e);
		}
	}
	
	private void daebookDisplay() {
		mod.setNumRows(0); // 표 초기화 시킴.
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			// 대출한 도서 확인
			sql = "select b_bun, b_jemok, b_daebun, c_irum, b_daeil " + 
				  "from book inner join customer on b_daebun=c_bun " +
				  "order by b_daeil desc, b_bun asc";
			pstmt = conn.prepareStatement(sql);
			rs1 = pstmt.executeQuery();
			//pstmt.setString(1, txtBbun.getText());
			int cou = 0;
			while(rs1.next()) {
				String [] imsi = { rs1.getString(1),
								   rs1.getString(2),
								   rs1.getString(3),
								   rs1.getString(4),
								   rs1.getString(5)
				};
				mod.addRow(imsi);
				cou++;
			}
			String [] imsi2 = {null, "전체건수 :" + cou};
			mod.addRow(imsi2);
			
		} catch (Exception e) {
			System.out.println("deabookDisplay err : " + e);
		} finally {
			try {
				if(rs1 != null) rs1.close();
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e3) {
				
			}
		}
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBbun) { // 반납 도서번호
			if(txtBbun.getText().equals("")) {
				txtBbun.requestFocus();
				JOptionPane.showMessageDialog(this,"반납 도서번호를 입력하시오");
				return;
			}
			
		try {
			// 도서 확인여부 SQL
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			sql = "select * from book where b_bun=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtBbun.getText());
			rs1 = pstmt.executeQuery();
			
			// 도서가 있는지 없는지 확인 여부
			if(!rs1.next()) { 
				JOptionPane.showMessageDialog(this, txtBbun.getText() + ": 등록된 도서번호가 아닙니다\n 확인바람");
				txtBbun.requestFocus();
				return;
			}
			
			txtBjemok.setText(rs1.getString("b_jemok"));
			
			// 정식으로 대여된 도서여부 확인(판단)
			if(rs1.getString("b_daeyn").equals("n")) {
				JOptionPane.showMessageDialog(this, txtBbun.getText() + "번은 정식으로 대여된 도서번호가 아닙니다\n 확인바람");
				txtBbun.setText("");
				txtJemok.setText("");
				txtBbun.requestFocus();
				return;
			}
			
			// 반납 진행
			txtBdaeil.setText(rs1.getString("b_daeil").substring(0, 10)); // substring 문자열을 0번자리 부터 10번 자리까지 보여줌.
			
			SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			String nalja = da.format(calendar.getTime());
			
//			String nalja = calendar.get(Calendar.YEAR) + "-" + 
//						   (calendar.get(Calendar.MONTH) + 1) + "-" + 
//						   calendar.get(Calendar.DATE);
			txtBbanil.setText(nalja);
			
			txtBdaebun.setText(rs1.getString("b_daebun")); // 책을 대여한 사람의 번호를 넘겨줌.
			
			// 반납되는 도서제목을 customer의 c_memo에서 제거----------------------
			// 대여된 도서제목을 표시 후 반납되는 제목 빼기
			sql = "select * from customer where c_bun=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtBdaebun.getText());
			rs2 = pstmt.executeQuery();
			rs2.next();
			txtJemok.setText(rs2.getString("c_memo"));
			
			String ban_jemok = txtJemok.getText();
			int start = ban_jemok.indexOf(txtBjemok.getText()); // Btxt 제목에 시작점 찾기
			int end = txtBjemok.getText().length(); // Btxt 끝점 찾기
			
			txtJemok.setSelectionStart(start); // 반납도서에 제목 시작점 
			txtJemok.setSelectionEnd(start + end + 1); // 도서 이름의 시작점에서 길이를 더하면 밑에서 잡아줌. +1를 하면 제목 뒤에 콤마를 제거.
			txtJemok.requestFocus(); // 도서이름의 시작점과 끝점을 잡음
			txtJemok.replaceSelection(""); // 반납도서 지우기
			
			// 마지막 콤마 제거
			try {
				String str = txtJemok.getText();
				int a = str.length() - 1;
				String ss = str.substring(a);
				if(ss.equals(",")) str = str.substring(0, a);
				txtJemok.setText(str);
				
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			//----------------------------------------------------------
			btnBannap.setEnabled(true);
		} catch (Exception e2) {
			// TODO: handle exception
		} finally {
			try {
				if(rs1 != null) rs1.close();
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e3) {
				// TODO: handle exception
			}
		}
			
		} else if(e.getSource() == btnChange) {
			BookCal bookCal = new BookCal(); // 변경을 누르면 캘린더를 띄어 날짜 선택 가능하게 함.
			calFrame = new JFrame("반납일 변경");
			calFrame.add(bookCal);
			calFrame.setBounds(300, 300, 250, 200);
			calFrame.setVisible(true);
			
		} else if(e.getSource() == btnBannap) {
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				
				// Customer update
				sql = "update customer set c_memo=? where c_bun=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtJemok.getText());
				pstmt.setString(2, txtBdaebun.getText());
				pstmt.executeUpdate();
				
				// Book update
				sql = "update book set b_daeyn='n', b_daebun=0, b_daeil=null, b_banil=? where b_bun=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtBbanil.getText());
				pstmt.setString(2, txtBbun.getText());
				pstmt.executeUpdate();
				
				btnBannap.setEnabled(false);
				btnNew.setEnabled(true);
				
				daebookDisplay(); // 갱신 후 대여자료 다시 읽기.
			} catch (Exception e2) {
				System.out.println("반납 오류 : " + e2);
			} finally {
				try {
					if(rs1 != null) rs1.close();
					if(rs2 != null) rs2.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e3) {
					
				}
			}
		} else if(e.getSource() == btnNew) { 
			txtBbun.setText("");
			txtBjemok.setText("");
			txtBdaeil.setText("");
			txtBbanil.setText("");
			txtBdaebun.setText("");
			txtJemok.setText("");
			txtBbun.requestFocus();
			btnNew.setEnabled(false);
			
		} else if(e.getSource() == btnClose) { // 닫기
			try {
				if(rs1 != null) rs1.close();
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("반납 닫기 오류: " + e2);
			} finally {
				BookMain.book_ban.setEnabled(true); // 메인화면의 대여메뉴 활성화
				BookMain.childWinBan.dispose(); // 메인화면에 있는 대여창 닫기
			}
		}
		
	}
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//도서정보 패널========================
		JPanel bookPn=new JPanel(new GridLayout(3,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel bPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun=new JTextField("",5);
		txtBjemok=new JTextField("",20);
		txtBdaeil=new JTextField("",10);
		txtBbanil=new JTextField("",10);
		txtBdaebun=new JTextField("",5);
		txtJemok=new JTextField("",25);  //반납되는 도서 제목을 고객메모에서 제거하기 위함
		
		btnBbun=new JButton("확인");
		btnBbun.setMargin(new Insets(0, 3, 0, 3));
		btnChange=new JButton("변경");
		btnChange.setMargin(new Insets(0, 3, 0, 3));

		bPn1.add(new JLabel("번호:"));
		bPn1.add(txtBbun);
		bPn1.add(btnBbun);
		
		bPn2.add(new JLabel("제목:"));
		bPn2.add(txtBjemok);
		txtBjemok.setEditable(false);
		
		bPn3.add(new JLabel("대여일:"));
		bPn3.add(txtBdaeil);	
		txtBdaeil.setEditable(false);
		bPn3.add(new JLabel("      반납일:"));
		bPn3.add(txtBbanil);
		bPn3.add(btnChange);	
		
		bPn4.add(new JLabel("대여자 번호:"));
		bPn4.add(txtBdaebun);
		bPn4.add(txtJemok);   //고객메모란의 대여도서 제목중 반납되는 비디오 제목만 제거하기 위해 사용  
		txtJemok.setVisible(true); //숨긴다.
		txtBdaebun.setEditable(false);
		
		JPanel bottomPn=new JPanel();
		bottomPn.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		btnBannap=new JButton("반납 확인");
		btnNew=new JButton("새로 입력");
		btnClose=new JButton(" 닫 기 ");
		
		bottomPn.add(btnBannap);
		bottomPn.add(btnNew);
		JLabel lbl=new JLabel("    "); 
		bottomPn.add(lbl);
		bottomPn.add(btnClose);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);	
		
		this.add(bookPn);
		this.add(bPn4);
		this.add(bottomPn);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//도서 목록 테이블 삽입
		String[][]data=new String[0][4];
		String []cols={"번호","제목","대번","이름","대여일"};
		mod=new DefaultTableModel(data,cols){ //테이블 내용 수정 불가
			    public boolean isCellEditable(int rowIndex, int mColIndex) {
				   return false;
				}
			   };
		JTable tab=new JTable(mod);
		tab.getColumnModel().getColumn(0).setPreferredWidth(20);
		tab.getColumnModel().getColumn(1).setPreferredWidth(150);
		tab.getColumnModel().getColumn(2).setPreferredWidth(20);
		tab.getColumnModel().getColumn(3).setPreferredWidth(30);
		tab.setSelectionBackground(Color.green);
		JScrollPane pa=new JScrollPane(tab);
		this.add(pa);
		
		btnBannap.setEnabled(false);  //반납 버튼 비활성화
		btnNew.setEnabled(false);     
	}
	
	public static void main(String[] args) {
		BookBannap bookBannap = new BookBannap();
		JFrame frame=new JFrame("반납 창");
		frame.getContentPane().add(bookBannap);
		frame.setResizable(false);
		frame.setBounds(200,200,500,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}