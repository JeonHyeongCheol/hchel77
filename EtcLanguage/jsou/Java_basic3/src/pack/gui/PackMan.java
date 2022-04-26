package pack.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JFrame;

public class PackMan extends JFrame implements KeyListener{
	Image image;
	int x, y; // 좌표
	int sel = 1; // 이미지 순서
	
	public PackMan() {
		super("상하좌우 화살표를 사용하세요");
		
		setLayout(null); // 레이아웃을 사용하지 않겠다는 것.
		setResizable(false); // 창의 크기를 조절하지 않도록 함.
		setIconImage(Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack1.jpg")); // 아이콘 변경하기.
		
		setBounds(200, 200, 300, 300);
		setBackground(Color.WHITE);
		setVisible(true);
		
		// 이미지를 불러올 때 위에 명령어 처럼 써야 하고, 이미지를 불러올 때 툴킷을 이용함.. 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		x = getWidth() / 2; // 센터잡기. 프레임 높이,너비에 나누기 2해서 중간에 위치 시킴.
		y = getHeight() / 2;
		// Window에 0,0은 왼쪽 맨 위에.
		
		addKeyListener(this); // frame에 keyListener 장착. 자판에 값을 받아 옴.
	}
	
	@Override
	public void keyTyped(KeyEvent e) {} // 사용하지 않음
	
	@Override
	public void keyReleased(KeyEvent e) {} // 사용하지 않음
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // 자판값을 받아 저장시킴.
		
		// 키 값을 확인하기 위해  키 이벤트에서 자판 값을 가져올 수 있음. 밑에 라인 처럼 추가하면 사용 가능.
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_NUMPAD6) { 
			//System.out.println("오른쪽");
			//if(sel == 1) sel = 2; else sel = 1; // sel이 1일 때 2의 값을 주고 2일 때는 1의 값을 주므로서 번갈아가면서 실행.
			sel = (sel == 1)?2:1; // 삼항 연산자를 사용해서 가능. 위에 라인이랑 같음.
			x = (x < getWidth())? x+=10 : -image.getWidth(this); 
			// 이미지 너비를 뺀 좌표의 값으로 넘어감. ex) 원래 widows 좌표는 0,0 그림너비 50이라 했을 때 0 - 50 = -50 그래서 x = -50. -50에서 시작
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_NUMPAD4) {
			sel = (sel == 3)?4:3; 
			x = (x > 0)? x-=10 : getWidth() + image.getWidth(this); 
			// 너비에 이미지 값 더하기. 더하지 않으면 안으로 들어와버림. ex) 프레임 너비 400 + 이미지값 50 => x = 450. 450에서 시작.
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_NUMPAD2) {
			sel = (sel == 5)?6:5; 
			y = (y < getHeight())? y+=10 : - image.getHeight(this);
		} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_NUMPAD8) {
			sel = (sel == 7)?8:7; 
			y = (y > 0)? y-=10 : getHeight() + image.getHeight(this);
		} 
		repaint(); // 갱신
	}
	
	@Override
	public void paint(Graphics g) { // Graphics는 붓이라고 생각해라.
		switch (sel) {
		case 1:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack1.jpg");
			break;
		case 2:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack2.jpg");
			break;
		case 3:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack3.jpg");
			break;
		case 4:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack4.jpg");
			break;
		case 5:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack5.jpg");
			break;
		case 6:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack6.jpg");
			break;
		case 7:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack7.jpg");
			break;
		case 8:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack8.jpg");
			break;
		}
		
		
		
		g.clearRect(0, 0, getWidth(), getHeight()); // 잔상 지우기
		g.drawImage(image, x - image.getWidth(this) / 2, // 센터에서 이미지 높이, 너비를 빼고 나누기 2하면 정확히 중간에 위치.
						   y - image.getHeight(this) / 2, this); 
	}
	
	// paint는 repaint하고 Update를 함.
	
	public static void main(String[] args) {
		new PackMan();
	}
}
