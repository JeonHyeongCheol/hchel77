package pack.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LayoutEx extends Frame implements ActionListener{
	
	Panel pn1 = new Panel(); // 프레임안에 속하는 컨테이너
	Panel pn2 = new Panel();
	Panel pn3 = new Panel();
	Panel pn4 = new Panel();
	Panel pn5 = new Panel();
	Button btnOk; // 버튼 사용
	CardLayout card = new CardLayout(); 
	TextField txtBunho, txtIrum; // 텍스트 컴포넌트. 무엇인가 입력할 때 사용.
	
	public LayoutEx() { // 생성자에 레이아웃을 만듬.
		// Frame은 기본이 Borderlayout임.
		// Borderlayout는 Center, East, West, North, South, East로 나뉨.
		// Layout는 1순위로 Flow 2순위로 Card를 많이 씀.
		
		setLayout(new GridLayout(2, 1)); // FlowLayout에서 GridLayout으로 2행 1열로 layout 변경.
		
		// 1번째 행
		
		Label lbl1 = new Label("bunho"); // 라벨은 제목, 메세지를 넣기 위해 씀.
		txtBunho = new TextField("10", 20);
		// Panel은 기본이 FlowLayout (좌에서 우로, 위에서 아래로).
		pn1.add(lbl1); // Label을 Panel에 배치
		pn1.add(txtBunho);
		pn1.setBackground(Color.YELLOW); // 첫 번째 행에 색을 줌.
		 // Panel을 프레임에 배치
		//add(pn1);
		
		Label lbl2 = new Label("Irum"); // 라벨은 메세지를 넣기 위해 씀.
		txtIrum = new TextField("홍길동", 20);
		pn2.add(lbl2); // Label을 Panel에 배치
		pn2.add(txtIrum);
		pn2.setBackground(Color.CYAN);
		//add(pn2);
		
		pn3.setLayout(card); // 카드 레이아웃은 겹쳐 놓는 것임. FlowLayout -> CardLayout으로 변경
		pn3.add("kbs", pn1); // 맨 처음에는 pn1이 뒤에 pn2가 있음.
		pn3.add("mbc", pn2);
		//add(pn3);
		btnOk = new Button("OK");
		btnOk.addActionListener(this); // 버튼을 눌렀을 때 actionPerformed로 감.
		
		pn4.add(pn3); // CardLayout이 다시 FlowLayout으로 바뀜(즉 pn1, pn2 Flow가 pn3에 Card로 p3는 pn4에 Flow로 저장).
		pn4.add(btnOk); // FlowLayout에 버튼이 생김.
		add(pn4);
		
		// 2번째 행	
		
		pn5.setBackground(Color.green);
		add(pn5); // FlowLayout
		pn5.setLayout(new BorderLayout()); // 레이아웃 변경시 setLayout 명령어 사용.
		pn5.add("Center", new Label("My Name is java", Label.CENTER)); // 라벨에는 정렬 방식을 줄수 있음.
		//    (border에 센터)                             (라벨을 센터로)
		pn5.add("East", new Label("East"));
		pn5.add("West", new Label("West"));
		pn5.add("South", new Label("South", Label.CENTER));
		pn5.add("North", new Label("North", Label.CENTER));
		
		setTitle("레이아웃 연습");
		setBounds(200, 200, 400, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setTitle("클릭");
		//System.out.println(e.getActionCommand()); // 버튼에 있는 Ok가 커맨드에 찍힘.
		if(e.getActionCommand().equalsIgnoreCase("OK")) {
			btnOk.setLabel("Click");
			card.show(pn3, "mbc");
		} else {
			btnOk.setLabel("OK");
			card.show(pn3, "kbs");
		}
		
	}
	
	public static void main(String[] args) {
		new LayoutEx(); // 순차적 프로그램 아님. 이벤트 지향
	}
}
