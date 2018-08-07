package kr.co.kic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookBook extends JPanel implements ActionListener{
	JTextField txtBbun,txtBjemok,txtBjang,txtBkuil,txtBdaesu,txtBdaeyn,txtBdaebun,txtBdaeil,txtBbanil;
	JTextArea taBmemo;
	JButton btnInsert,btnUpdate,btnDel,btnFind,btnOption,btnClose;
	JButton btnF,btnP,btnN,btnL,btnin,btnsu;
	JLabel lblRec,lblPic;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1, rs2;
	
	String sql, imgPath; //--- 그림 파일 경로 변수
	int iTotal = 0;	// 자료의 갯수
	int iLast = 0; 	// 마지막 레코드 번호
	JPanel picPn;
	File file;  //--- 이미지 로딩하기 위한 변수
	boolean isInsert = false;	// Insert 버튼 눌림 여부
	boolean isUpdate = false;	// Update 버튼 눌림 여부
	
	public BookBook(){
		design();
		addListener();
		accDb();
		
		init();
		display();
	}
	
	private void addListener() {
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDel.addActionListener(this);
		btnFind.addActionListener(this);
		btnOption.addActionListener(this);
		btnClose.addActionListener(this);
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
		//btnin.addActionListener(this);
		btnsu.addActionListener(this);
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
	}
	
	private void init() {
		try {
			sql = "select * from book order by b_bun asc";
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE); // 스크롤 가능이면서 수정 가능
			rs1 = pstmt.executeQuery();
			rs1.last(); // 포인터를 마지막에 있도록 함.
			iTotal = rs1.getRow(); // 행의 전체 갯수를 알기위해 행의 위치에 있는 값을 가져옴으로서 몇 개인지 알 수 있음.
			//System.out.println("전체 자료수 : " + iTotal);
			iLast = rs1.getInt("b_bun"); // 마지막 도서번호 얻기 : 신규 도서 등록 시 사용
			rs1.first(); // 포인터를 다시 처음으로 가져다 놓음.
			
		} catch (Exception e) {
			System.out.println("init err : " + e);
		}
	}
	
	private void display() {
		try {
			txtBbun.setText(rs1.getString("b_bun"));
			//txtBdaeyn.setText(rs1.getString("b_daeyn"));
			if(rs1.getString("b_daeyn").equals("y")) {
				txtBdaeyn.setText("대여중");
				txtBdaeyn.setForeground(Color.RED);
			} else {
				txtBdaeyn.setText("비치중");
				txtBdaeyn.setForeground(Color.BLACK);
			}
			txtBjemok.setText(rs1.getString("b_jemok"));
			txtBjang.setText(rs1.getString("b_jang"));
			if(rs1.getString("b_kuipil") == null) { // SQL에서 들어오는 값이 null일 경우 Err가 떨어질 수 있어서 그 Err를 막고자 미리 방지한 것.
				txtBkuil.setText("");
			} else {
				txtBkuil.setText(rs1.getString("b_kuipil").substring(0, 10));
			}
			txtBdaesu.setText(rs1.getString("b_daesu"));
			txtBdaebun.setText(rs1.getString("b_daebun"));
			if(rs1.getString("b_daeil") == null) { // java에서 업뎃한거는 ""로 안들어가고 null로 들어가는듯...?
				txtBdaeil.setText("");
			} else {
				txtBdaeil.setText(rs1.getString("b_daeil").substring(0, 10));
			}
			if(rs1.getString("b_banil") == null) {
				txtBbanil.setText("");
			} else {
				txtBbanil.setText(rs1.getString("b_banil").substring(0, 10));
			}
			taBmemo.setText(rs1.getString("b_memo"));
			
			lblRec.setText(rs1.getRow() + "/" + iTotal);
			
			// 이미지 출력
			imgPath = rs1.getString("b_image");
			dispImage();
		} catch (Exception e) {
			System.out.println("Book display err : " + e);
		}
	}
	
	private void dispImage() {
		if(imgPath != null) { // 널이 아닌 것만
			String dir = "C:\\work\\jsou\\Java_jdbc_final_book\\src\\bookimage\\";
			//System.out.println(dir + imgPath);
			ImageIcon icon = new ImageIcon(dir + imgPath);
			lblPic.setIcon(icon);
			lblPic.setToolTipText("이미지 파일명 : " + imgPath); // 이미지 위에 마우스를 올리면 경로가 나옴.
			if(icon != null) {
				lblPic.setText(null); // 이미지가 있으면 라벨에 Text 없이 그림만 뜨게함.
			} else {
				lblPic.setText("이미지 없음"); // 이미지가 없으면 라벨에 이미지 없음을 출력.
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) { // 신규 도서
			if(isInsert == false) {
				btnInsert.setText("확인");
				isInsert = true;
				
				txtBbun.setText(iLast + 1 + "");
				txtBjemok.setEditable(true);
				txtBjang.setEditable(true);
				txtBkuil.setEditable(true);
				taBmemo.setEditable(true);
				
				SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
//				String imsi = calendar.get(Calendar.YEAR) + "-" + 
//							  (calendar.get(Calendar.MONTH) +1) + "-" +
//							  calendar.get(Calendar.DATE);
				String imsi = Format.format(calendar.getTime());
				txtBjemok.setText(null);
				txtBjang.setText(null);
				txtBkuil.setText(imsi);
				txtBdaeyn.setText(null);
				txtBdaesu.setText("0");
				txtBdaebun.setText(null);
				txtBdaeil.setText(null);
				txtBbanil.setText(null);
				taBmemo.setText(null);
				taBmemo.setBackground(Color.WHITE);
				
				imgPath = null; // 도서 이미지 경로 초기화
				
				// 이미지를 이미지 추가버튼으로 대체
				picPn.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
				btnin = new JButton("그림 삽입");
				btnin.addActionListener(this);
				lblPic.setVisible(false); // container가 보이는지 안보이는지 확인.
				picPn.add("Center",btnin);
				
				txtBjemok.requestFocus();
			} else {
				btnInsert.setText("신규");
				isInsert = false;

				// 신규 도서 등록 작업
				try {
					sql = "insert into book values(?,?,?,?,0,'n',0,null,null,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtBbun.getText());
					pstmt.setString(2, txtBjemok.getText());
					pstmt.setString(3, txtBjang.getText());
					pstmt.setString(4, txtBkuil.getText());
					pstmt.setString(5, taBmemo.getText());
					if(file.getName() == null || file.getName().equals("")) {
						pstmt.setString(6, null);
					} else {
						pstmt.setString(6, file.getName());
					}
					pstmt.executeUpdate();
					
					txtBjemok.setEditable(false);
					txtBjang.setEditable(false);
					txtBkuil.setEditable(false);
					taBmemo.setEditable(false);
					taBmemo.setBackground(Color.LIGHT_GRAY);
					
					init();
					rs1.last();
					display();
				} catch (Exception e2)           
				{
					System.out.println("도서 추가 오류: " + e2);
				}
//				btnOk.setEnabled(false);
//				
//				txtCirum.setEditable(false);
								
				//display();
			}
		} else if(e.getSource() == btnin) { // 이미지 삽입
			JFileChooser chooser = new JFileChooser("C:\\work\\jsou\\Java_jdbc_final_book\\src\\bookimage\\");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // 파일 선택용으로
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.CANCEL_OPTION) {
				file = null;
			} else {
				file = chooser.getSelectedFile();
			}
			//System.out.println(file.getName());
			imgPath = file.getName();
			dispImage();
			
			lblPic.setVisible(true);
			btnin.setVisible(false);
		} else if(e.getSource() == btnUpdate) {
			if (isUpdate == false) {
				isUpdate = true;
				btnUpdate.setText("완료");

				txtBjemok.setEditable(true);
				txtBjang.setEditable(true);
				taBmemo.setEditable(true);
				taBmemo.setBackground(Color.WHITE);
				// 수정시 부수 작업 필요하나 생략...
			} else {
				isUpdate = false;
				btnUpdate.setText("수정");

				// 수정 처리
				
				try {
					sql = "update book set b_jemok=?, b_jang=?, b_memo=? where b_bun=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtBjemok.getText());
					pstmt.setString(2, txtBjang.getText());
					pstmt.setString(3, taBmemo.getText());
					pstmt.setString(4, txtBbun.getText());
					pstmt.executeUpdate();
					
					int currentRow = rs1.getRow();
					init(); // 수정된 자료 출력을 위해
					rs1.absolute(currentRow); // 레코드 포인트를 해당위치로 이동 그 지점에 자료를 display() 하면 수정된 자료를 보임.
					display();
					
				} catch (Exception e2) {
					System.out.println("도서 수정 오류 : " + e2);
				}
				
				txtBjemok.setEditable(false);
				txtBjang.setEditable(false);
				taBmemo.setEditable(false);
				taBmemo.setBackground(Color.LIGHT_GRAY);
			}
		} else if(e.getSource() == btnDel) {
			int re = JOptionPane.showConfirmDialog(this, "정말로 삭제할까요?", "삭제", JOptionPane.YES_NO_CANCEL_OPTION);
			if(re == JOptionPane.YES_OPTION) {
				try {
					if(rs1.getRow() == 0) {
						JOptionPane.showMessageDialog(this, "삭제할 고객이 없습니다");
						return; // 작업할 것이 없기 때문에 return.
					}
					
					// 현재 도서가 대여중인 것은 안됨.
					if(txtBdaeyn.getText().equals("비치중")) {
						int currentRow = rs1.getRow();
						sql = "delete from book where b_bun=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, txtBbun.getText());
						pstmt.executeUpdate();
						
						init();
						if(currentRow == 1) { // 첫번째 도서이면 그냥 그대로
							
						} else {
							rs1.absolute(currentRow - 1); // 다른 행에서 삭제시 레코드 1 감소시킨 후 출력
						}
						display();
					} else {
						JOptionPane.showMessageDialog(this, "반납이 완료된 도서만 삭제가 가능합니다");
					}
					
					
				} catch (Exception e2) {
					System.out.println("고객 삭제 오류 : " + e2);
				}
			}
		} else if(e.getSource() == btnFind) {
			// 도서명으로 검색
			String fName = JOptionPane.showInputDialog(this, "검색할 도서명을 입력하세요");
			//if(fBun.equals("")) return;
			if(fName == null) return;
			
			try {
				int currentRow = rs1.getRow(); // 검색결과가 없는 경우를 대비.
				rs1.beforeFirst(); // 검색 결과가 없는 경우 다시 돌아감.
				
				boolean find = false;
				while(rs1.next()) {
					String ss = rs1.getString("b_jemok");
					if(fName.equals(ss)) {
						display();
						find = true;
						break;
					}
				}
				if (find == false) {
					JOptionPane.showMessageDialog(this, "검색결과가 없습니다");
					rs1.absolute(currentRow);
				}
				
			} catch (Exception e2) {
				System.out.println("고객 찾기 오류 : " + e2);
			}
		} else if(e.getSource() == btnOption) {
			
		} else if(e.getSource() == btnClose) { // 닫기 
			try {
				if(rs1 != null) rs1.close();
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("도서 닫기 오류: " + e2);
			} finally {
				BookMain.book_book.setEnabled(true); // 메인화면의 대여메뉴 활성화
				BookMain.childWinBook.dispose(); // 메인화면에 있는 대여창 닫기
			}
		} else if(e.getSource() == btnsu) {
			
		}
		
		try {
			if(e.getSource() == btnF) {
				rs1.first();
				display();				
			} else if(e.getSource() == btnP) {
				if(rs1.isFirst()) {
					JOptionPane.showMessageDialog(this, "자료의 처음 또는 마지막입니다"); // 사용자에게 보여줘야하는 메세지.
					return; // 맨 처음에서 그 전으로 갈려고 하면 return 함.
				}
				rs1.previous();
				display();
			} else if(e.getSource() == btnN) {
				if(rs1.isLast()) {
					JOptionPane.showMessageDialog(this, "자료의 처음 또는 마지막입니다"); // 사용자에게 보여줘야하는 메세지.
					return; // 맨 마지막에서 더 갈려고 하면 return 함.
				}
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
		
		//도서정보 패널========================
		JPanel bookPn=new JPanel(new GridLayout(6,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel bPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn5=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn6=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun=new JTextField("",5);
		txtBjemok=new JTextField("",30);
		txtBjang=new JTextField("",10);
		txtBkuil=new JTextField("",10);
		txtBdaesu=new JTextField("",5);
		txtBdaeyn=new JTextField("",5);
		txtBdaebun=new JTextField("",5);
		txtBdaeil=new JTextField("",10);
		txtBbanil=new JTextField("",10);
		taBmemo=new JTextArea(2,30);
		JScrollPane scroll=new JScrollPane(taBmemo);
		taBmemo.setBackground(Color.lightGray);
		
		txtBbun.setEditable(false);
		txtBjemok.setEditable(false);
		txtBjang.setEditable(false);
		txtBkuil.setEditable(false);
		txtBdaesu.setEditable(false);
		txtBdaeyn.setEditable(false);
		txtBdaebun.setEditable(false);
		txtBdaeil.setEditable(false);
		txtBbanil.setEditable(false);
		taBmemo.setEditable(false);
				
		btnInsert=new JButton("신규");
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
		bPn1.add(new JLabel("번호 :"));
		bPn1.add(txtBbun);
		bPn1.add(new JLabel("                              대여표시 :"));
		bPn1.add(txtBdaeyn);
		
		bPn2.add(new JLabel("제목 :"));
		bPn2.add(txtBjemok);	

		bPn3.add(new JLabel("장르 :"));
		bPn3.add(txtBjang);
		bPn3.add(new JLabel("                구입일 :"));
		bPn3.add(txtBkuil);
		
		bPn4.add(new JLabel("대여횟수 :"));
		bPn4.add(txtBdaesu);
		bPn4.add(new JLabel("                 대여자번호 :"));
		bPn4.add(txtBdaebun);
		
		bPn5.add(new JLabel("대여일 :"));
		bPn5.add(txtBdaeil);
		bPn5.add(new JLabel("            반납일 :"));
		bPn5.add(txtBbanil);

		bPn6.add(new JLabel("메모 :"));
		bPn6.add(scroll);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);
		bookPn.add(bPn4);  bookPn.add(bPn5); 	bookPn.add(bPn6);
		this.add(bookPn);
		
		//이미지 패널----------------------------------------------------
		picPn=new JPanel(new BorderLayout());
		lblPic = new JLabel();
		//lblPic.setPreferredSize(new Dimension(1000, 300));
		picPn.add(lblPic);

		this.add(picPn);

		//레코드 이동 패널------------------------------------------------
		JPanel movePn=new JPanel();
		movePn.setBorder(new TitledBorder(new TitledBorder("레코드 이동"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		movePn.add(btnF);
		movePn.add(btnP);
		movePn.add(lblRec);
		movePn.add(btnN);
		movePn.add(btnL);
		
		this.add(movePn);
		
        //명령 버튼 패널--------------------------------------------------
		JPanel bottomPn1=new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		bottomPn1.add(btnInsert);
		bottomPn1.add(btnUpdate);
		bottomPn1.add(btnDel);
		bottomPn1.add(btnFind);
		bottomPn1.add(btnOption);
		bottomPn1.add(btnClose);
		
		this.add(bottomPn1);
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnsu=new JButton("그림 수정");
		//btnin=new JButton("");

	}
	

	public static void main(String[] args){
		BookBook bookBook =new BookBook();
		
		JFrame frame=new JFrame("도서 창");
		frame.getContentPane().add(bookBook);
		frame.setBounds(440,110,430,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
