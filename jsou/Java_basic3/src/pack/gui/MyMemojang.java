package pack.gui;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyMemojang extends JFrame implements ActionListener { // 버튼이 있기 때문에 어떠한 액션이 있어야 함.
	
	JButton btnCopy = new JButton("copy"); // 모든 객체 만들 때 변수명은 이용한 객체에 세글자에 내용을 적을 것.
	JButton btnPaste = new JButton("paste");
	JButton btnCut = new JButton("cut");
	JButton btnDel = new JButton("del");
	JPanel pn = new JPanel();
	JTextArea txtMemo = new JTextArea("");
	String copyText;
	
	// 메뉴 아이템
	JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit;
	JMenuItem mnuCopy, mnuPaste, mnuCut, mnuDel;
	JMenuItem mnuAbout, mnuEtc1, mnuEtc2;
	
	// 팝업 메뉴 - 마우스에 왼쪽과 오른쪽 구분
	JPopupMenu popup;
	JMenuItem mWhite, mBlue, mYellow;
	
	public MyMemojang() {
		super("제목없음 - 메모장");
		initLayout();
		menuLayout();
		
		setBounds(200, 200, 400, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(MyMemojang.this, "정말로 종료 할까요?", "종료", JOptionPane.YES_NO_OPTION); // 내부 무명클래스이기 때문에 args는 메모장 args임.
				if(re == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 종료하지 않고 그냥 계속 사용.
				}
			}
		});
	}
	
	private void initLayout() {
		pn.add(btnCopy);
		pn.add(btnCut);
		pn.add(btnPaste);
		pn.add(btnDel);
		add("South", pn); // 판넬에 아래쪽에 버튼을 집어 넣을 것임.
		JScrollPane pane = new JScrollPane(txtMemo); // 스크롤 기능이 있는 JScrollpane을 씀. args는 txtMemo
		//add("Center", pane); // 센터에는 txtArea를 집어 넣음.
		add("Center", pane);
		
		btnCopy.addActionListener(this);
		btnCut.addActionListener(this);
		btnPaste.addActionListener(this);
		btnDel.addActionListener(this);
		
	}
	
	private void menuLayout() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu mnuFile = new JMenu("파일"); // 주메뉴
		mnuNew = new JMenuItem("새로 만들기"); // 부메뉴
		mnuOpen = new JMenuItem("열기...");
		mnuSave = new JMenuItem("저장...");
		mnuExit = new JMenuItem("종료");
		mnuFile.add(mnuNew);
		mnuFile.add(mnuOpen);
		mnuFile.add(mnuSave);
		mnuFile.addSeparator(); // 구분선 만들기
		mnuFile.add(mnuExit);
		
		JMenu mnuEdit = new JMenu("편집"); // 주메뉴
		mnuCopy = new JMenuItem("복사하기"); // 부메뉴
		mnuPaste = new JMenuItem("붙여넣기");
		mnuCut = new JMenuItem("잘라내기");
		mnuDel = new JMenuItem("삭제하기");
		mnuEdit.add(mnuCopy);
		mnuEdit.add(mnuPaste);
		mnuEdit.add(mnuCut);
		mnuEdit.add(mnuDel);
		
		JMenu mnuHelp = new JMenu("도움말"); // 주메뉴
		mnuAbout = new JMenuItem("메모장 정보..."); // 부메뉴
		JMenu mnuEtc = new JMenu("기타"); // 부메뉴에 부메뉴 만들기
		mnuEtc1 = new JMenuItem("계산기"); 
		mnuEtc2 = new JMenuItem("메모장");
		mnuEtc.add(mnuEtc1);
		mnuEtc.add(mnuEtc2);
		mnuHelp.add(mnuAbout);
		mnuHelp.add(mnuEtc);
		
		
		menubar.add(mnuFile); // 메뉴바에 메뉴 장착
		menubar.add(mnuEdit);
		menubar.add(mnuHelp);
		setJMenuBar(menubar); // Frame에 메뉴바 장착
		
		mnuNew.addActionListener(this);
		mnuOpen.addActionListener(this);
		mnuSave.addActionListener(this);
		mnuExit.addActionListener(this);
		mnuCopy.addActionListener(this);
		mnuPaste.addActionListener(this);
		mnuCut.addActionListener(this);
		mnuDel.addActionListener(this);
		mnuAbout.addActionListener(this);
		mnuEtc1.addActionListener(this);
		mnuEtc2 .addActionListener(this);
		
		// 팝업메뉴
		popup = new JPopupMenu();
		JMenu mColor = new JMenu("색상선택");
		mWhite = new JMenuItem("하양");
		mBlue = new JMenuItem("파랑");
		mYellow = new JMenuItem("노랑");
		mColor.add(mWhite);
		mColor.add(mBlue);
		mColor.add(mYellow);
		popup.add(mColor);
		txtMemo.add(popup); // txtarea에서 하는 거기 때문에 txtarea에 add.
		
		mWhite.addActionListener(this);
		mBlue.addActionListener(this);
		mYellow.addActionListener(this);
		
		txtMemo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getModifiers() == MouseEvent.BUTTON3_MASK) { // 마우스 오른쪽 버튼
					popup.show(txtMemo, e.getX(), e.getY()); // 클릭 한 곳에 x,y에 팝업 메뉴가 뜸.
				}
			}
		});
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCopy || e.getSource() == mnuCopy) {
			//System.out.println(txtMemo.getSelectedText()); // getSelectedText() : 선택한 값을 사용하는 명령어
			copyText = txtMemo.getSelectedText();
		} else if (e.getSource() == btnPaste  || e.getSource() == mnuPaste) {
			//txtMemo.setText(copyText); // 이렇게하면 덮어쓰기 됨.
			int p = txtMemo.getCaretPosition(); // 커서가 있는 지점을 변수로 넘겨 줌.
			txtMemo.insert(copyText, p); // 커서가 있는 지점에 넣어 줌.
		} else if (e.getSource() == btnCut  || e.getSource() == mnuCut) {
			copyText = txtMemo.getSelectedText(); // 블럭 잡기.
			
			int start = txtMemo.getSelectionStart(); // 범위에 시작점을 얻을 수 있음.
			int end = txtMemo.getSelectionEnd(); // 범위에 마지막점을 얻을 수 있음.
			txtMemo.replaceRange("", start, end); // 시작과 끝점을 잘라냄.
		} else if (e.getSource() == btnDel || e.getSource() == mnuDel) {
			int start = txtMemo.getSelectionStart();
			int end = txtMemo.getSelectionEnd(); 
			txtMemo.replaceRange("", start, end);
		} else if (e.getSource() == mnuNew) {
			txtMemo.setText("");
			this.setTitle("제목없음 - 메모장");
		} else if (e.getSource() == mnuOpen) {
			FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD); // Windos 열기 프레임
			dialog.setDirectory("."); // 현재 이 파일이 있는 곳이 열림. ex) "c:\\root" << 요렇게 설정 할 수도 있음.
			dialog.setVisible(true); // 창은 열리지만 저장 되는 것은 아님. 
			if(dialog.getFile() == null) return;
			String dfName = dialog.getDirectory() + dialog.getFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(dfName));
				txtMemo.setText("");
				String line;
				while((line = reader.readLine()) != null) {// 널이 아닌동안 읽기 작업
					txtMemo.append(line + "\n"); // 한 줄씩 메모 Area에 추가
				}
				reader.close();
				this.setTitle(dialog.getFile() + " - 메모장");
			} catch (Exception e2) {
				System.out.println("열기 에러 : " + e2);
			}
		} else if (e.getSource() == mnuSave) {
			FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE); // Windos 저장 프레임
			dialog.setDirectory("."); // 현재 이 파일이 있는 곳이 열림. ex) "c:\\root" << 요렇게 설정 할 수도 있음.
			dialog.setVisible(true); // 창은 열리지만 저장 되는 것은 아님. 
			if(dialog.getFile() == null) return; 
			//System.out.println("박스반환 정보 : " + dialog.getDirectory() + dialog.getFile()); 경로 및 파일명을 가져오는 것임.
			String dfName = dialog.getDirectory() + dialog.getFile(); // 선택한 디렉토리 및 파일 명.
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(dfName)); // 버퍼를 이용해 속도를 높여줌.
				writer.write(txtMemo.getText()); // 텍스트 area에 있는 모든 문자열을 넘겨서 저장함.
				writer.close();
				
				this.setTitle("abc.txt - 메모장");
			} catch (Exception e2) {
				System.out.println("저장 오류 : " + e2);
			}
			
		} else if (e.getSource() == mnuExit) {
			int re = JOptionPane.showConfirmDialog(MyMemojang.this, "정말로 종료할까요?", "종료", JOptionPane.YES_NO_OPTION);
			if(re == JOptionPane.YES_OPTION) {
				//setVisible(false);
				//dispose(); 
				System.exit(0); // 위에꺼 쓰든 밑에꺼 쓰든 똑같음.
			}
		} else if (e.getSource() == mnuAbout) {
			new MemoAbout(this); 
			//System.out.println("창 호출 후");
		} else if (e.getSource() == mnuEtc1) {
			try {
				Runtime runtime = Runtime.getRuntime(); // 실행시키는 명령어.
				runtime.exec("calc.exe");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		} else if (e.getSource() == mnuEtc2) {
			try {
				Runtime.getRuntime().exec("notepad.exe"); // 위에 etc1과 같은거지만 코드를 줄여 봄.
			} catch (Exception e2) {
				// TODO: handle exception
			}
		} else if (e.getSource() == mWhite) {
			txtMemo.setBackground(Color.WHITE);
		} else if (e.getSource() == mBlue) {
			txtMemo.setBackground(Color.BLUE);
		} else if (e.getSource() == mYellow) {
			txtMemo.setBackground(Color.YELLOW);
		}
		
		txtMemo.requestFocus(); // 실행 후에도 포커스는 메모장에 하도록 함.
		
	}
	
	public static void main(String[] args) {
		new MyMemojang();

	}

}
