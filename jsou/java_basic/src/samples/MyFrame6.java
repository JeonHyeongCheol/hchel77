package samples;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame6 extends Frame {
	public MyFrame6() {
		setTitle("내부 클래스 연습");
		setSize(300, 200);
		setLocation(200, 200);
		setVisible(true);
		
		//내부 익명(무명) 클래스 사용
		// 아규먼트에 리턴 값이 있다고 생각해야 함.
		addWindowListener(new WindowAdapter() { // 어댑터를 new를 하는 것은 아님. 아규먼트에 리턴 값이 있다고 생각하고 무명클래스를 만들어 간단하게 사용.
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.GREEN);
			}
		});
		
	}
	
	public static void main(String[] args) {
		new MyFrame6();
	}
}
