package samples;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame4 extends WindowAdapter { 
	// WindowAdapter를 사용하면 상속을 다 받지 않고 하나만 오버라이드 받아서 사용 가능. 
	// extends 이기 때문에 다중으로 상속받지는 못함.
	private Frame frame; // 프레임형 변수 설정.
	
	public MyFrame4() {
		frame = new Frame("Adapter(추상)클래스 사용"); // 변수 객체 설정.
		frame.setSize(400, 250);
		frame.setLocation(200, 250);
		frame.setVisible(true);
		frame.addWindowListener(this); // 객체변수(frame).명령어를 받아서 사용(ex.addwindowListener).
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new MyFrame4();
	}

}
