package lambdatest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonEvent extends JFrame{

	public ButtonEvent() {
		super("이벤트 람다 사용");
	
		setLayout(null);
		JButton btn1 = new JButton("click1");
		btn1.setBounds(10, 50, 100, 50);
		add(btn1);
		
		setBounds(200, 200, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 전통적 방식
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle("첫번째 버튼");
			}
		});
		
		JButton btn2 = new JButton("click2");
		btn2.setBounds(10, 150, 100, 50);
		add(btn2);
		btn2.addActionListener(e -> setTitle("두번째 버튼")); 
		// 람다식 사용 할 때 메소드가 하나인지 확인 하면 됨(Ctrl + SpaceBar 눌러서 하나면 인자 가져와서 바로 사용 가능).
		// 두번째 방법은 new 클래스명 드래그 후 마우스 오른쪽 클릭 후 Quick Fix 사용하면 람다식으로 바뀜.
		
		// Debug
		JButton btn3 = new JButton("눌러");
		btn3.setBounds(10, 250, 100, 50);
		add(btn3);
		
		btn3.addActionListener(new ActionListener() {
			int cou = 0;
			int tot = 0;			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++ ) {
					cou += 1;
					//System.out.println("cou : " + cou); // 변수 값 확인 방법1. Console 출력.
					//JOptionPane.showMessageDialog(ButtonEvent.this, "cou : " + cou); // 변수 값 확인 방법2.
					tot += cou;
				}
				setTitle("합은 " + tot);
			}
		});
	}	
	
	public static void main(String[] args) {
		new ButtonEvent();
	}

}
