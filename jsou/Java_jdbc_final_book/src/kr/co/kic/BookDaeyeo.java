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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class BookDaeyeo extends JPanel implements ActionListener{
	JTextField txtCbun,txtCirum,txtCjunhwa,txtCjuso,txtBbun,txtBjemok,txtBdaeil;
	JTextArea taCmemo;
	JButton btnCbun,btnCirum,btnCjunhwa,btnBbun,btnBjemok,btnDaeyeo,btnNew,btnClose;
	
	
	boolean bg=false, bv=false;  // 대여확인 버튼 활성화 여부 // 고객, 도서 둘다 true 일 경우 대여확인을 할 수 있도록.
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1, rs2;
	String sql;
	
	public BookDaeyeo(){
		design();
		addListener();
		accDb();
	}
	
	private void addListener() { // 버튼에 대한 Listener 장착.
		btnCbun.addActionListener(this);
		btnCirum.addActionListener(this);
		btnCjunhwa.addActionListener(this);
		btnBbun.addActionListener(this);
		btnBjemok.addActionListener(this);
		btnDaeyeo.addActionListener(this);
		btnNew.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	private void accDb() { // DB 연결 메소드.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Daeyeo accDB err : " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCbun)) { // 고객번호 버튼
		// (e.getSource().equals(btnCbun)) 요렇게 써도 됨. 
		// getActioncommand는  equals만 가능(==는 불가능)
			if(txtCbun.getText().equals("")) {
				txtCbun.requestFocus();
				JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
				return;
			}
			
			sql = "select * from customer where c_bun=?";
			
			processCustomer(txtCbun.getText());
			
		} else if(e.getSource() == btnCirum) { // 고객이름 버튼
			if(txtCirum.getText().equals("")) {
				txtCirum.requestFocus();
				JOptionPane.showMessageDialog(this, "고객이름을 입력하세요");
				return;
			}
			
			sql = "select * from customer where c_irum=?";
			
			processCustomer(txtCirum.getText());
		
		} else if(e.getSource() == btnCjunhwa) { // 고객전화 버튼
			if(txtCjunhwa.getText().equals("")) {
				txtCjunhwa.requestFocus();
				JOptionPane.showMessageDialog(this, "고객전화번호를 입력하세요");
				return;
			}
			
			sql = "select * from customer where c_junhwa=?";
			
			processCustomer(txtCjunhwa.getText());
			
		} else if(e.getSource() == btnBbun) { // 도서번호 버튼
			if(txtBbun.getText().equals("")) {
				txtBbun.requestFocus();
				
				JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
				return;
			}
			
			sql = "select * from book where b_bun=?";
			
			processBook(txtBbun.getText());
			
		} else if(e.getSource() == btnBjemok) { // 도서제목 버튼
			if(txtBjemok.getText().equals("")) {
				txtBjemok.requestFocus();
				JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
				return;
			}
			
			sql = "select * from book where b_jemok=?";
			
			processBook(txtBbun.getText());
			
		} else if(e.getSource() == btnDaeyeo) { // 대여확인 버튼
			// 도서 제목을 메모란에 표시 후 대여 수정에 참여
			//taCmemo.setText(txtBjemok.getText().trim()); // trim() : 공백자르기, 제목을 가져와 메모란에 넘겨서 써줌.
			if(taCmemo.getText().equals("")) {
				taCmemo.setText(txtBjemok.getText().trim()); // 첫 번째 도서, 아무것도 빌리지 않을 때.
			} else { // 이미 대여도서가 있는 상태, 한 개 이상을 빌렸을 때.
				taCmemo.setText(taCmemo.getText().trim() + "," + txtBjemok.getText().trim()); 
				// 기존에 메모에 있던 대여 책에 하나 더 추가 시켜줌(구분은 ,(콤마)로).
			}
			
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				// 둘 다 오류 없으면 커밋, 둘 중에 하나라도 이상 있으면 커밋하지 않음. -> 이런 것이 Transaction.
				// 여기서 stmt를 한 번쓰는 이유는 둘 다 되야지 commit 할 수 있도록 하기 위해.
				// Customer update
				sql = "update customer set c_daesu=c_daesu + 1, c_memo=? where c_bun=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, taCmemo.getText());
				pstmt.setString(2, txtCbun.getText());
				pstmt.executeUpdate();
				bg = false; // 도서 대여 후 bg, bv를 false를 줘야 함.
				
				// Book update 
				sql = "update book set b_daesu=b_daesu + 1, b_daeyn='y', b_daebun=?, b_daeil=? where b_bun=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtCbun.getText());
				pstmt.setString(2, txtBdaeil.getText());
				pstmt.setString(3, txtBbun.getText());
				pstmt.executeUpdate();
				bv = false;
				
				btnDaeyeo.setEnabled(false); // 대여 후 버튼 비활성화
				btnNew.setEnabled(true); // 대여 후 새로입력 클릭시 활성화
			} catch (Exception e2) {
				System.out.println("대여 갱신 오류 : " + e);
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
			txtCbun.setText("");
			txtCirum.setText(""); // 텍스트에 DB에서 가져온 가져온 값을 줌.
			txtCjunhwa.setText("");
			txtCjuso.setText("");
			taCmemo.setText("");
			
			txtBbun.setText("");
			txtBjemok.setText(""); 
			txtBdaeil.setText("");
		} else if(e.getSource() == btnClose) { // 닫기
			try {
				if(rs1 != null) rs1.close();
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("대여 닫기 오류: " + e2);
			} finally {
				BookMain.book_dae.setEnabled(true); // 메인화면의 대여메뉴 활성화
				BookMain.childWinDae.dispose(); // 메인화면에 있는 대여창 닫기
			}
		}
		
	}
	
	private void processCustomer(String fData) { // 문자열 타입의 데이터 값을 가져옴.
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fData);
			
			rs1 = pstmt.executeQuery();
			if(rs1.next()) {
				txtCbun.setText(rs1.getString("c_bun"));
				txtCirum.setText(rs1.getString("c_irum")); // 텍스트에 DB에서 가져온 가져온 값을 줌.
				txtCjunhwa.setText(rs1.getString("c_junhwa"));
				txtCjuso.setText(rs1.getString("c_juso"));
				taCmemo.setText(rs1.getString("c_memo"));
				bg = true; // 고객을 만족함.
				
				btnDaeyeo.setEnabled(bg && bv); // 두 개다 만족하면 버튼 Enable(true) 시킴.
				
			} else {
				JOptionPane.showMessageDialog(this, "등록된 고객이 아닙니다");
				txtCbun.setText("");
				txtCirum.setText("");
				txtCjunhwa.setText("");
				txtCjuso.setText("");
				taCmemo.setText("");
				bg = false; // 고객을 만족하지 않음.
				btnDaeyeo.setEnabled(false);
			} 
		} catch (Exception e2) {
			System.out.println("processCustomer err : " + e2);
		} finally {
			try {
				if(rs1 != null) rs1.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e3) {
				
			}
		}
	}
	
	private void processBook(String fData) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fData);
			
			rs2 = pstmt.executeQuery();
			
			if(rs2.next()) {
				txtBbun.setText(rs2.getString("b_bun"));
				txtBjemok.setText(rs2.getString("b_jemok"));
				
				// 대여 가능여부 판단 ------------------------------------------
				if(rs2.getString("b_daeyn").equals("y")) { // 대소문자 구분하는 명령어는 ?
					JOptionPane.showMessageDialog(this, "현재 대여중인 도서입니다.");
					txtBbun.setText(null);
					txtBjemok.setText(null);
					txtBdaeil.setText(null);
					return; // 작업 종료
				}
				//-------------------------------------------------------
				
				bv = true; // 도서 만족 
				btnDaeyeo.setEnabled(bg && bv);
				
				Date today = new Date();
				SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd"); 
				// 대소문자 구분해야 함. DD를 입력하면 1월 1일 부터 시작하여 다 더한 일수 출력해줌.
				String nalja = format.format(today); // 포맷은 ?
				txtBdaeil.setText(nalja); // 대여 날짜를 줌.
			} else {
				JOptionPane.showMessageDialog(this, "등록된 도서가 아닙니다");
				txtBbun.setText("");
				txtBjemok.setText(""); 
				txtBdaeil.setText("");
				bv = false;
				btnDaeyeo.setEnabled(false);
			} 
		} catch (Exception e2) {
			System.out.println("processBook err : " + e2);
		} finally {
			try {
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e3) {
				
			}
		}
	}
	

	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//고객정보 패널========================
		JPanel customerPn=new JPanel(new GridLayout(3,2));
		customerPn.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel cPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));

		txtCbun=new JTextField("",5);
		txtCirum=new JTextField("",10);
		txtCjunhwa=new JTextField("",10);
		txtCjuso=new JTextField("",20);
		txtCjuso.setEditable(false);
		
		taCmemo=new JTextArea(2,20);
		JScrollPane scroll=new JScrollPane(taCmemo,	ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		taCmemo.setEditable(false);
		taCmemo.setBackground(Color.lightGray);
		
		btnCbun=new JButton("확인1");
		btnCbun.setMargin(new Insets(0, 3, 0, 3));
		btnCirum=new JButton("확인2");
		btnCirum.setMargin(new Insets(0, 3, 0, 3));
		btnCjunhwa=new JButton("확인3");
		btnCjunhwa.setMargin(new Insets(0, 3, 0, 3));

		cPn1.add(new JLabel("번호:"));
		cPn1.add(txtCbun);
		cPn1.add(btnCbun);
		
		cPn2.add(new JLabel("이름:"));
		cPn2.add(txtCirum);	
		cPn2.add(btnCirum);
		
		cPn3.add(new JLabel("전화:"));
		cPn3.add(txtCjunhwa);
		cPn3.add(btnCjunhwa);
		
		cPn4.add(new JLabel("주소:"));
		cPn4.add(txtCjuso);
		
		customerPn.add(cPn1); customerPn.add(cPn2); customerPn.add(cPn3);
		customerPn.add(cPn4); 
		customerPn.add(new JLabel("고객이 대여한 도서  ☞   ",JLabel.RIGHT));
		customerPn.add(scroll); 
		scroll.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 5));
		this.add(customerPn);
				
		//도서정보 패널========================
		JPanel bookPn=new JPanel(new GridLayout(3,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		
		JPanel bPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun=new JTextField("",5);
		txtBjemok=new JTextField("",20);
		txtBdaeil=new JTextField("",10);
		btnBbun=new JButton("확인1");
		btnBbun.setMargin(new Insets(0, 3, 0, 3));
		btnBjemok=new JButton("확인2");
		btnBjemok.setMargin(new Insets(0, 3, 0, 3));

		bPn1.add(new JLabel("번호:"));
		bPn1.add(txtBbun);
		bPn1.add(btnBbun);
		
		bPn2.add(new JLabel("제목:"));
		bPn2.add(txtBjemok);
		bPn2.add(btnBjemok);
		
		bPn3.add(new JLabel("대여일:"));
		bPn3.add(txtBdaeil);	
		
		JPanel bottomPn=new JPanel();
		bottomPn.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		btnDaeyeo=new JButton("대여 확인");
		btnNew=new JButton("새로 입력");
		btnClose=new JButton(" 닫 기 ");
		
		bottomPn.add(btnDaeyeo);
		bottomPn.add(btnNew);
		JLabel lbl=new JLabel("    ");  //버튼 사이에 공백 부여
		bottomPn.add(lbl);
		bottomPn.add(btnClose);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);	
		
		this.add(customerPn);
		this.add(bookPn);
		this.add(bottomPn);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnDaeyeo.setEnabled(false); //대여 버튼 비활성화
		btnNew.setEnabled(false);
	}

	public static void main(String[] args) { // 편의상 넣음. 모두 완료 후 삭제 해야 함. BookMain에서만 돌아가도록.
		BookDaeyeo daeyeo=new BookDaeyeo();
		JFrame frame=new JFrame("대여 창");
		frame.getContentPane().add(daeyeo);
		//frame.setResizable(false);
		frame.setBounds(200,200,580,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
