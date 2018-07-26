package samples;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame5 extends Frame { // 여기서 Frame을 extends 하고 있기 때문에 더 못함. 그래서 안쪽에 클래스를 만들어 extends 하여 사용!!
	
	String str[] = {"백용", "대환", "동훈", "다희", "주현"};
	int x,y;
	
	public MyFrame5() {
		//super("내부 클래스 연습");
		setTitle("내부 클래스 연습");
		setSize(300, 200);
		setLocation(200, 200);
		setVisible(true);
		
		Wevent we = new Wevent(); // 내부 클래스를 사용하여 windowadapter를 extend하고 객체를 새로 만들어 주어서 사용 하면 됨. 
		addWindowListener(we);
		addMouseListener(new Mevent()); // 객체를 따로 만들지 않고 바로 사용 하려면 안에 new Mevent());
		
	}
	
	class Wevent extends WindowAdapter { // 내부 클래스 // WindowAdapter는 addWindowListener를 포함하고 있음.
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
	
	class Mevent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			//setBackground(Color.YELLOW);
//			int x = e.getX(); // 폼안에 좌표 값
//			int y = e.getY();
			x = e.getX(); // 클릭시 받는 x,y 좌표
			y = e.getY();
			setTitle(x + ", " + y);
			paint(getGraphics()); // 그림 그리는 메소드 사용
			repaint(); // 초기화 한 후 paint 메소드 호출
		}
	}
	
	@Override
		public void paint(Graphics g) { // 그림그리는 메소드
			g.setColor(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255))); // 글씨 색깔 변경
			g.setFont(new Font("맑은 고딕", Font.BOLD, (int)(Math.random() * 50 * 8))); // 글씨 폰트 변경
			g.drawString(str[1], x, y); // 변수에 넘겨서 사용
			
		}
	
	public static void main(String[] args) {
		new MyFrame5();
	}
 
}
