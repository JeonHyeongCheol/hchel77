package kr.co.kic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BookEtcCustomer2DBar extends JPanel{
	Connection conn;
	Statement stmt;
	ResultSet rs;
	String sql;
	
	Vector<String> vecName = new Vector<String>();
	//고객의 이름을 저장하는 벡터..
	Vector<Integer> vecInt = new Vector<Integer>();	
	//고객의 대여횟수를 저장하는 벡터..
	public BookEtcCustomer2DBar() {
		design();
	}
	
	
	public void design(){
       
	}
    
	public static void main(String[] args) {
		BookEtcCustomer2DBar bookEtcCustomer2DBar=new BookEtcCustomer2DBar();
		JFrame frame=new JFrame();
		frame.getContentPane().add(bookEtcCustomer2DBar);
		frame.setBounds(200,200,580,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
