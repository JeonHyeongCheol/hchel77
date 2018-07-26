package samples;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.SynchronousQueue;

public class MyFrame3 extends Frame implements WindowListener, MouseListener{ // 상속하고 그 뒤에 인터페이스 추가 가능.
	// WindowsListener 사용시 7개의 추상메소드를 사용하여야지 가능.
	public MyFrame3() {
		super("인터페이스 연습");
		
		addWindowListener(this); // 아규먼트를 하나 받아야지 사용 가능. 현재 Frame에 WindowListener 장착. this는 windowListener임.
		addMouseListener(this); // 여기서 this는 MouseListener이고 MyFrame3을 뜻함. addMouseListener는 Frame에 있음.
	}
	
	private void abc() {
		setTitle("상속 연습");        
		setLocation(200, 150);
		setSize(300, 250);
		setVisible(true);
	}
	
	// WindowListener 내의 7개의 추상메소드를 오버라이드 하여야지 사용가능 함.
	
	@Override
		public void windowActivated(WindowEvent e) { // 메이커 측에서 이벤트 핸들러 메소드명을 변경 할 수 없도록 만듬. Java에서 정해놓음.
			
		}
	
	@Override
		public void windowClosed(WindowEvent e) {
		}
	
	@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	
	@Override
		public void windowDeactivated(WindowEvent e) {
		}
	
	@Override
		public void windowDeiconified(WindowEvent e) {
			System.out.println("화면 복귀");
		}
	
	@Override
		public void windowIconified(WindowEvent e) {
			System.out.println("화면 아이콘화");
		}
	
	@Override
		public void windowOpened(WindowEvent e) {
		}
	
	// MouseListener 5개의 메소드를 오버라이드 하여야 함.
	//int count = 0;
	@Override
		public void mouseClicked(MouseEvent e) {
			//int count = 0; 지역변수이기 때문에 계속 초기화 되기 때문에 전역변수로 써야 함.
			//System.out.println("폼 바닥 클릭 수 : " + (count += 1));
		
			//setBackground(Color.BLUE); // 쓰러지는 글자는 public static final
			//setBackground(new Color(255, 5, 5)); // 빛의 3원색인 R, G, B를 생성자로 받아 색을 표현
			System.out.println((int)(Math.random() * 255));
			int r = (int)(Math.random() * 255);
			int g = (int)(Math.random() * 255);
			int b = (int)(Math.random() * 255);
			setBackground(new Color(r, g, b));
			setTitle(r + " " + g + " " + b);
		}
	
	@Override
		public void mouseEntered(MouseEvent e) {
		}
	
	@Override
		public void mouseExited(MouseEvent e) {
		}
	
	@Override
		public void mousePressed(MouseEvent e) {
		}
	@Override
		public void mouseReleased(MouseEvent e) {
		}
	
	
	//
	public static void main(String[] args) {
		MyFrame3 frame3 = new MyFrame3();                          
		frame3.abc();
		
	}

}
