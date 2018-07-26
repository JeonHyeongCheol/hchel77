package pack.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingEx extends JFrame implements ActionListener{
	// Swing 이용한 JFrame
	JLabel lblShow;
	int count = 0;
	
	public SwingEx() {
		setTitle("Swing Test");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1)); // FlowLayout이 였는데 Grid쓰면서 2행 1열로 쪼갬.
		//panel.setBackground(Color.YELLOW);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); //  여백주기. Factory가 있으면 스스로 new를 하고 있다는 얘기.
		
		// Panel의 첫째 행
		JButton button = new JButton("클릭(C)");
		button.setMnemonic(KeyEvent.VK_C); // Access Key. 키보드에서 입력 했을 때 사용 될 수 있도록 추가
		panel.add(button); // panel의 첫 행에 버튼을 집어 넣음.
		button.addActionListener(this);
		
		// Panel의 두번째 행
		lblShow = new JLabel("버튼 클릭 수 : " + count);
		panel.add(lblShow); // panel의 두 번째 행에 넣음.
		
		//getContentPane().add(panel, BorderLayout.CENTER); // JFrame 옛날에는 getcontentpane를 써야지 add가 가능했지만 버전업 되면서 그냥 add써도 가능.
		//add("Center", panel); // add 할 수 있는 방법은 두 가지. 이 줄에 있는 것고 밑에 있는 줄.
		add(panel, BorderLayout.CENTER);
		
		
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이 명령어 사용시 알아서 종료. awt랑은 다름.
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		lblShow.setText("버튼 클릭수 : " +  count); // 위에 정의 한대로 해줘야지 나옴.
		
	}

	
	public static void main(String[] args) {
		new SwingEx();
	}
}
