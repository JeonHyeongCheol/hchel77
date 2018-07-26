package pack.gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EventEx extends Frame implements ActionListener{
	// 이벤트 사용 방법
	private Button btn1 = new Button("btn1");
	private Button btn2 = new Button("btn2");
	private Button btn3 = new Button("btn3");
	private Button btn4 = new Button("btn4");
	private Button btn5 = new Button("btn5(exit)");
	
	
	public EventEx() {
		super("이벤트 연습");
		
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		makelayout();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void makelayout( ) {
		add("East", btn1);
		add("West", btn2);
		add("Center", btn3);
		add("South", btn4);
		add("North", btn5);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(new MyEvent()); // Action을 사용했기 때문에 ActionListener를 사용해야 함.
		btn4.addMouseListener(new OurEvent()); // 마우스를 사용했기 떄문에 MouseListener를 사용해야 함.
		
		// 이벤트 처리 방법 4 - 내부 무명(익명)클래스 사용(btn5 사용)
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}
	
	// 이벤트 처리 방법 1 - 해당 클래스에서 implement를 사용해 actionPerformed 사용 (btn1 사용)
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand()); // 둘중에 아무거나 사용해도 상관없음.
		//System.out.println(e.getSource());
		
		//if(e.getActionCommand().equals("btn")); // 이줄을 사용하거나 밑에 줄을 사용해도 상관없음.
		if(e.getSource() == btn1 ) {
			setTitle("버튼1 클릭");
		} else if (e.getSource() == btn2) {
			setTitle("버튼2 선택");
		}
	}
	
	// 이벤트 처리 방법 2 - 내부클래스 사용 1(btn3 사용)
	class MyEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EventEx.this.setTitle("세번째 버튼이라네"); // EventEx에 있는 setTitle 변경. 그냥 setTitle해도 상관없음.
		}
	}
	
	// 이벤트 처리 방법 3 - 내부클래스 사용 2(btn4 사용)
	class OurEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			setTitle("네번째 버튼이라네");
		}
	}
	
	public static void main(String[] args) {
		new EventEx();
	}
}
