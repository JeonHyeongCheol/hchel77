package pack.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SwingMenu extends JPanel implements ActionListener { // Jpanel도 상속 받을 수 있음. 패널을 여러개 만들 때 사용

	JButton btnR, btnG, btnB;
	JTextArea txtArea = new JTextArea("", 10, 50); // 10행 50열 짜리 text를 만듬.
	JMenuBar mBar;
	JMenuItem mnuMes, mnuOk, mnuInput;

	public SwingMenu() {
		setLayout(new BorderLayout()); // Flow -> Border로 변경
		btnR = new JButton("빨강");
		btnG = new JButton("초록");
		btnB = new JButton("파랑");
		btnR.addActionListener(this);
		btnG.addActionListener(this);
		btnB.addActionListener(this);

		JPanel jPanel = new JPanel();
		jPanel.add(btnR);
		jPanel.add(btnG);
		jPanel.add(btnB);
		add("South", jPanel);
		add("Center", txtArea);

		menuShow();
	}

	private void menuShow() { // Menu
		mBar = new JMenuBar();
		JMenu menu = new JMenu("메뉴");
		mnuMes = new JMenuItem("메세지");
		mnuOk = new JMenuItem("확인");
		mnuInput = new JMenuItem("입력");
		menu.add(mnuMes);
		menu.add(mnuOk);
		menu.add(mnuInput);

		mBar.add(menu); // MenuBar는 프레임이에 넣어줘야 함.

		// 메뉴 리스너 장착
		mnuMes.addActionListener(this);
		mnuOk.addActionListener(this);
		mnuInput.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnR ) {
			txtArea.setBackground(Color.RED);
		} else if (e.getSource() == btnG) {
			txtArea.setBackground(new Color(0, 255, 0));
		} else if(e.getSource() == btnB ) {
			txtArea.setBackground(new Color(0, 0, 255));
		} else if(e.getSource() == mnuMes) {
			// 객체.메서드()
			// 클래스가 메서드를 바로 부를 수 있다.
			//JOptionPane.메서드() 형태로 사용(중간에 객체 만드는 과정 없음!!)

			JOptionPane.showMessageDialog(this, "메세지 창", "알림", JOptionPane.INFORMATION_MESSAGE); 
			// JOptionPane.showMessageDialog (args, 메세지 내용, 타이틀, 아이콘) 사용시 메세지 창 뜸.
			System.out.println("창이 닫히기 전까지 수행 안함");
		} else if(e.getSource() == mnuOk) {
			int re = JOptionPane.showConfirmDialog(this, "버튼 선택", "선택", JOptionPane.YES_NO_CANCEL_OPTION);
			System.out.println(re);
			/*//이렇게 잘 사용하지 않음
			switch (re) {
			case 0:
				txtArea.setText("예 선택");
				break;
			case 1:
				txtArea.setText("아니오 선택");
				break;
			case 2:
				txtArea.setText("취소 선택 선택");
				break;
			}
			*/
			switch (re) {
			case JOptionPane.YES_OPTION:
				txtArea.append("예 선택"); // txtArea 추가함. Append는 추가, setText는 덮어쓰기
				break;
			case JOptionPane.NO_OPTION:
				txtArea.append("아니오 선택");
				break;
			case JOptionPane.CANCEL_OPTION:
				txtArea.append("취소 선택 선택");
				break;
			} 
		} else if(e.getSource() == mnuInput) {
				String ss = JOptionPane.showInputDialog(this, "자료 입력");
				txtArea.append(ss + "\n");
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("메뉴 & 대화상자"); // Frame

		SwingMenu panel1 = new SwingMenu(); // Panel

		// frame.getContentPane().add(panel1); // 옛날에 사용하는 방법.
		frame.add(panel1); // Panel을 JFrame에 배치. 이 클래스는 패널이기 때문에 Main에 프레임을 새로 만들어서 띄어주어야 함.
		frame.setJMenuBar(panel1.mBar); // Jframe에 Menu 배치.
		frame.setBounds(200, 200, 300, 200);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
