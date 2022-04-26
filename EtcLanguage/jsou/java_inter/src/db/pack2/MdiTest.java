package db.pack2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class MdiTest extends JFrame implements ActionListener{

	JMenuItem mnuNew, mnuExit;
	JInternalFrame childWin; // 내부 프레임 만듬.
	JDesktopPane desktopPane = new JDesktopPane(); // 내부 프레임을 열기위한 도화지(?)라고 생각하면 됨.
	
	public MdiTest() {
		setTitle("Mdi 연습");
		
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("파일");
		mnuNew = new JMenuItem("새창");
		mnuExit = new JMenuItem("종료");
		mnuFile.add(mnuNew);
		mnuFile.add(mnuExit);
		mbar.add(mnuFile);
		setJMenuBar(mbar);
		
		mnuNew.addActionListener(this);
		mnuExit.addActionListener(this);
		
		setBounds(200, 200, 400, 300);
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("새창")) {
			getContentPane().add(desktopPane); 
			// 메뉴바를 만들면 나머지 공간은 contentpane 공간이 됨. 그 안에 desktoppane을 집어 넣음.
			createListen();
			desktopPane.add(childWin);
			childWin.setLocation(10,10);
			childWin.show();
		} else if(e.getActionCommand().equals("종료")) {
			System.exit(0);
		}
	}
	
	private void createListen() {
		childWin = new JInternalFrame("자식 창", true, true, true, true); // 최소화, 최대화 등
		childWin.getContentPane().setLayout(new BorderLayout());
		childWin.setSize(300, 200);
		
		childWin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // JInternalFrame 창 닫기
	}
	
	
	public static void main(String[] args) {
		new MdiTest();

	}

}
