package kr.co.kic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class BookCustomer extends JPanel implements ActionListener {
	JTextField txtCbun,txtCirum,txtCjunhwa,txtCjuso,txtCdaesu;
	JTextArea taCmemo;
	JButton btnInsert,btnOk,btnUpdate,btnDel,btnFind,btnOption,btnClose;
	JButton btnF,btnP,btnN,btnL;
	JLabel lblRec;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1, rs2;
	String sql;
	
	int iTotal = 0;	// 자료의 갯수
	int iLast = 0; 	// 마지막 레코드 번호
	boolean isInsert = false;	// Insert 버튼 눌림 여부
	boolean isUpdate = false;	// Update 버튼 눌림 여부
	
	// 생성자
	public BookCustomer(){
		design();
		addListener();
		accDb();
		
		init();
		display();
	}
	
	private void addListener() {
		btnInsert.addActionListener(this);
		btnOk.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDel.addActionListener(this);
		btnFind.addActionListener(this);
		btnOption.addActionListener(this);
		btnClose.addActionListener(this);
		
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 레코드 이동을 계속 해야하기 때문에 DB(Conn)를 열어주고 있어야함.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("Customer accDb err : " + e);
		}
	}
	
	private void init() {
		try {
			sql = "select * from customer order by c_bun asc";
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE); // 스크롤 가능이면서 수정 가능
			rs1 = pstmt.executeQuery();
			rs1.last(); // 포인터를 마지막에 있도록 함.
			iTotal = rs1.getRow(); // 행의 전체 갯수를 알기위해 행의 위치에 있는 값을 가져옴으로서 몇 개인지 알 수 있음.
			//System.out.println("전체 자료수 : " + iTotal);
			iLast = rs1.getInt("c_bun"); // 마지막 고객번호 얻기 : 신규 고객 등록 시 사용
			rs1.first(); // 포인터를 다시 처음으로 가져다 놓음.
			
		} catch (Exception e) {
			System.out.println("init err : " + e);
		}
	}
	
	private void display() {
		try {
			txtCbun.setText(rs1.getString("c_bun"));
			txtCirum.setText(rs1.getString("c_irum"));
			txtCjunhwa.setText(rs1.getString("c_junhwa"));
			txtCjuso.setText(rs1.getString("c_juso"));
			txtCdaesu.setText(rs1.getString("c_daesu"));
			taCmemo.setText(rs1.getString("c_memo"));
			lblRec.setText(rs1.getRow() + " / " + iTotal); // 이거는 순서 1부터 ~ 해서 순서를 알려줌.
		} catch (Exception e) {
			System.out.println("display err : " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) {
			
		} else if(e.getSource() == btnOk) {
			
		} else if(e.getSource() == btnUpdate) {
			
		} else if(e.getSource() == btnDel) {
			
		} else if(e.getSource() == btnFind) {
			
		} else if(e.getSource() == btnOption) {
			
		} else if(e.getSource() == btnClose) { // 닫기 
			try {
				if(rs1 != null) rs1.close();
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("고객 닫기 오류: " + e2);
			} finally {
				BookMain.book_customer.setEnabled(true); // 메인화면의 대여메뉴 활성화
				BookMain.childWinCustomer.dispose(); // 메인화면에 있는 대여창 닫기
			}
		} 
		
		try {
			if(e.getSource() == btnF) {
				rs1.first();
				display();				
			} else if(e.getSource() == btnP) {
				if(rs1.isFirst()) return; // 맨 처음에서 그 전으로 갈려고 하면 return 함.
				rs1.previous();
				display();
			} else if(e.getSource() == btnN) {
				if(rs1.isLast()) return; // 맨 마지막에서 더 갈려고 하면 return 함.
				rs1.next();
				display();
			} else if(e.getSource() == btnL) {
				rs1.last();
				display();
			}			
		} catch (Exception e2) {
			System.out.println("레코드 이동 오류 : " + e2);
		}
	}
	
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//고객정보 패널========================
		JPanel customerPn=new JPanel(new GridLayout(4,1));
		customerPn.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel cPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtCbun=new JTextField("",5);
		txtCirum=new JTextField("",10);
		txtCjunhwa=new JTextField("",15);
		txtCjuso=new JTextField("",28);
		txtCdaesu=new JTextField("",5);
		taCmemo=new JTextArea(2,28);
		JScrollPane scroll=new JScrollPane(taCmemo);
		taCmemo.setBackground(Color.lightGray);
		
		txtCbun.setEditable(false);
		txtCirum.setEditable(false);
		txtCjunhwa.setEditable(false);
		txtCjuso.setEditable(false);
		txtCdaesu.setEditable(false);
		taCmemo.setEditable(false);
		
		btnInsert=new JButton("신규");
		btnOk=new JButton("확인");
		btnUpdate=new JButton("수정");
		btnDel=new JButton("삭제");
		btnFind=new JButton("검색");
		btnOption=new JButton("옵션");
		btnClose=new JButton("닫기");
		btnF=new JButton(" <<= ");
		btnP=new JButton("  <= ");
		btnN=new JButton(" =>  ");
		btnL=new JButton(" =>> ");
		lblRec=new JLabel(" 0 / 0 ",JLabel.CENTER);
		cPn1.add(new JLabel("번호 :"));
		cPn1.add(txtCbun);
		cPn1.add(new JLabel("       이름 :"));
		cPn1.add(txtCirum);
		
		cPn2.add(new JLabel("전화 :"));
		cPn2.add(txtCjunhwa);	
		cPn2.add(new JLabel("      대여횟수 :"));
		cPn2.add(txtCdaesu);
		
		cPn3.add(new JLabel("주소 :"));
		cPn3.add(txtCjuso);
		
		cPn4.add(new JLabel("메모 :"));
		cPn4.add(scroll);

		customerPn.add(cPn1);  customerPn.add(cPn2); 	customerPn.add(cPn3);	customerPn.add(cPn4);
		this.add(customerPn);
		
		btnOk.setEnabled(false);
		
		//레코드 이동 패널========================
		JPanel movePn=new JPanel();
		movePn.setBorder(new TitledBorder(new TitledBorder("레코드 이동"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		movePn.add(btnF);
		movePn.add(btnP);
		movePn.add(lblRec);
		movePn.add(btnN);
		movePn.add(btnL);
		
		this.add(movePn);
		
        //명령 버튼 패널========================
		JPanel bottomPn1=new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		bottomPn1.add(btnInsert);
		bottomPn1.add(btnOk);
		JLabel lbl1=new JLabel("    "); 
		bottomPn1.add(lbl1);
		bottomPn1.add(btnUpdate);
		bottomPn1.add(btnDel);
		
		JPanel bottomPn2=new JPanel();
		bottomPn2.add(btnFind);
		bottomPn2.add(btnOption);
		JLabel lbl2=new JLabel("                          "); 
		bottomPn2.add(lbl2);
		bottomPn2.add(btnClose);
		
		this.add(bottomPn1);
		this.add(bottomPn2);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	public static void main(String[] args) {
		BookCustomer bookCustomer=new BookCustomer();
		JFrame frame=new JFrame("고객 창");
		frame.getContentPane().add(bookCustomer);
		frame.setBounds(200,200,430,450);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}