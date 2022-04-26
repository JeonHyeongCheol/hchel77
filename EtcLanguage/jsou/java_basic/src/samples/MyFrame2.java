package samples;

import java.awt.Frame;

public class MyFrame2 extends Frame{ // extends 뒤에 올 수 있는 클래스는 하나만 가능. 다중 상속 X
	
	
	public MyFrame2() {
//		super("상속 연습");                  //제목은 Frame 클래스의 private 맴버  (부모를 통해서 가능). 생성자가 생성자를 불러 올 때.
		
//		setLocation(200, 150);
//		setSize(500,300);
//		setVisible(true);
	}
	
	private void abc() {
		setTitle("상속 연습");               //setter 로 주입 .        안에서 자체적으로도 가능
		setLocation(200, 150);
		setSize(500,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		//new MyFrame2();                                          op쓰는 제일 큰 이유 = 자원의 재 활용
		MyFrame2 frame2 = new MyFrame2();                          //따로 불러서 사용할수 있다.
		frame2.abc();
		
	}

}
