package pack.thread;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class ThreadTest3 extends Frame implements ActionListener, Runnable{
	Label lbl; // 라벨 객체를 만듬.
	Thread thread; // 스레드 객체를 만듬.
	boolean b = false;
	
	public ThreadTest3( ) {
		// 스레드를 이용한 디지털 시계
		lbl = new Label("Display DateTime", Label.CENTER);
		add("Center", lbl); // lbl을 가운데 찍히게 하기 
		
		Button btnClick = new Button("click"); // 지역변수임. 최대한 지역변수를 많이 써라.
		add("South", btnClick); // 프레임 아래에 버튼 하나를 넣는다.
		btnClick.addActionListener(this); 
		// 이것을 사용하기 위해 actionListener을 추가하여야지 가능함. 현재클래스에서 장착하고 사용. 밑에 메소드추가하여 사용(actionperformed).
		// 버튼 클릭시 -> actionPerformed 으로 감.
		
		setTitle("스레드 시계");
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				b = true;
				System.exit(0);
			}
		});
		
		thread = new Thread(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // 폼에 시간 띄우기
		if(thread.isAlive()) return; // 스레드가 사용중이면 그냥 넘어감. 스레드를 다시 시작하지 않음. 이 문장이 없을 경우 스레드를 계속 만들게 됨.
		
		thread.start(); // 이것만 하게 되면 응용 프로그램은 종료하지만 스레드가 계속 돌기 때문에 break를 씀.
	}
	
	@Override
	public void run() {
		while(true) {
			if(b == true) break; // 종료 할 때 변수 b가 true이면 스레드를 종료 시킴.
			
			calendarShow();
			try {
				Thread.sleep(1000); // 1초가 1000
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private void calendarShow() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1; 
		int d = cal.get(Calendar.DATE);
		int h = cal.get(Calendar.HOUR);
		int mi = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		lbl.setText(y + "-" + m + "-" + d + "-" + " " + h + ":" + mi + ":" + s);
		lbl.setFont(new Font("궁서", Font.BOLD, 20));
	}
	
	public static void main(String[] args) {
		new ThreadTest3();
	}
}
