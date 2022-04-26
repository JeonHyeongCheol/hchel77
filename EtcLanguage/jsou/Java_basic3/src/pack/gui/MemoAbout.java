package pack.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MemoAbout extends JDialog implements ActionListener{ // 다이얼로그 상속. 혼자 뜰 수 없음.
	public MemoAbout(JFrame frame) { // 부모가 있어야 함. JFrame을 객체로 둠. 프레임이 호출하면 뜸. 
		//super(frame, "메모장이란?", true); 
		// true : modal dialog(창이 닫히기 전까지 아무 실행도 안됨. 멈춤.) false : modeless dialog(창이 안닫혀도 다 됨, 포커스 변경된다는 뜻)
		// 거의 기본적으로 이런 Modal을 씀.
		
		super(frame); // 위에랑 같은 내용
		setTitle("메모장이란?");
		setModal(true); 
		
		JLabel lblMsg = new JLabel("내 메모장 ver 0.9",JLabel.CENTER);
		JButton btnOk = new JButton("확인");
		btnOk.addActionListener(this);
		this.add("Center", lblMsg);
		add("South", btnOk);
		
		setBackground(Color.LIGHT_GRAY);
		setBounds(300, 300, 150, 150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose(); // JDialog 닫기
	}
}
