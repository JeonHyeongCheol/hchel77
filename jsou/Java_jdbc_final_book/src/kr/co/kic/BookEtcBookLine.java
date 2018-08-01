package kr.co.kic;

import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BookEtcBookLine extends JPanel{

	Vector<String> vecName = new Vector<String>();
	Vector<Integer> vecInt = new Vector<Integer>();	
	
	public BookEtcBookLine() {
		design();
	}

	public void design(){
	    this.setLayout(new BorderLayout());
  
	}


	public static void main(String[] args) {
		BookEtcBookLine bookEtcBookLine=new BookEtcBookLine();
		JFrame frame=new JFrame();
		frame.getContentPane().add(bookEtcBookLine);
		frame.setBounds(200,200,580,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}