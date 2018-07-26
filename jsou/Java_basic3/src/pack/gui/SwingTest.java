package pack.gui;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SwingTest extends JFrame implements ActionListener {
	JTextField txtName, txtAge;
	JLabel lblResult;
	ButtonGroup buttonGroup = new ButtonGroup(); // 라디오버튼 때문에 버튼그룹을 만들어 줘야 함.
	JRadioButton rdoM, rdoF;
	
	public SwingTest() {
		super("이벤트 연습");
		layInit();
		
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit() {
		setLayout(new GridLayout(5, 1));
		
		// 1행
		JLabel lbl1 = new JLabel("이름 : ");
		txtName = new JTextField("", 20);
		JPanel pn1 = new JPanel();
		pn1.add(lbl1);
		pn1.add(txtName);
		add(pn1);
		
		// 2행
		JLabel lbl2 = new JLabel("나이 : ");
		txtAge = new JTextField("", 20);
		JPanel pn2 = new JPanel();
		pn2.add(lbl2);
		pn2.add(txtAge);
		add(pn2);
		
		// 3행
		rdoM = new JRadioButton("남자", true);
		rdoF = new JRadioButton("여자", false);
		buttonGroup.add(rdoM);
		buttonGroup.add(rdoF);
		JPanel pn3 = new JPanel();
		pn3.add(new JLabel("성별선택 :"));
		pn3.add(rdoM);
		pn3.add(rdoF);
		add(pn3);
		
		// 4행
		JButton btnOk = new JButton("확인");
		btnOk.addActionListener(this);
		JPanel pn4 = new JPanel();
		pn4.add(btnOk);
		add(pn4);
		
		// 5행
		lblResult = new JLabel("결과 : ", JLabel.CENTER);
		add(lblResult);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 입력자료 유효성 검사
		if(txtName.getText().equals("")) { // Name칸에 빈칸이 있을 때 메세지 출력
			JOptionPane.showMessageDialog(this, "이름을 입력하시오"); // 메세지 창 출력
			txtName.requestFocus(); // 다시 작업 할 수 있도록 Name으로 돌아감.
			return;
		}
		
		if(txtAge.getText().equals("")) { // Name칸에 빈칸이 있을 때 메세지 출력
			JOptionPane.showMessageDialog(this, "나이를 입력하시오"); // 메세지 창 출력
			txtAge.requestFocus(); // 다시 작업 할 수 있도록 Name으로 돌아감.
			return;
		}
		// 나이 숫자 여부 판단
		int nai = 0;
		try {
		nai = Integer.parseInt(txtAge.getText()); // 텍스트에 있는 문자열을 받아옴. 숫자로 나타낼 수 있도록 파싱
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "나이는 숫자만 허용");
			txtAge.requestFocus();
			return;
		}
		
		//System.out.println(rdoM.isSelected() + " " + rdoF.isSelected()); // 남자 여자 선택 했을 때 확인
		
		String gender = "";
		if(rdoM.isSelected()) {
			gender = "남자";
		} else {
			gender = "여자";
		}
		
		String ss = "결과 : " + txtName.getText() + "님의 나이는 " + txtAge.getText() + " " + gender; 
		lblResult.setText(ss);
	}
	
	public static void main(String[] args) {
		new SwingTest();
	}
}
