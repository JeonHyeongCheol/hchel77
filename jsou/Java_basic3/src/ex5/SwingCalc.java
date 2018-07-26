package ex5;

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

public class SwingCalc extends JFrame implements ActionListener {
	JTextField txtnum1, txtnum2;
	JLabel lblResult;
	ButtonGroup btng = new ButtonGroup();
	JRadioButton plus, minus, mult, div;
	JButton calc, clear, exit;

	// 생성자
	public SwingCalc() {
		super("미니 계산기");
		layinit();

		setBounds(400, 400, 400, 400);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void layinit() {
		setLayout(new GridLayout(5, 1));

		// 1행
		JLabel lbl1 = new JLabel("숫자 1 :");
		txtnum1 = new JTextField("", 20);
		JPanel pa1 = new JPanel();
		pa1.add(lbl1);
		pa1.add(txtnum1);
		add(pa1);

		// 2행
		JLabel lbl2 = new JLabel("숫자 2 : ");
		txtnum2 = new JTextField("", 20);
		JPanel pa2 = new JPanel();
		pa2.add(lbl2);
		pa2.add(txtnum2);
		add(pa2);

		// 3행
		plus = new JRadioButton("더하기", true);
		minus = new JRadioButton("빼기", false);
		mult = new JRadioButton("곱하기", false);
		div = new JRadioButton("나누기", false);
		btng.add(plus);
		btng.add(minus);
		btng.add(mult);
		btng.add(div);
		JPanel pa3 = new JPanel();
		JLabel lbl3 = new JLabel("연산선택 : ");
		pa3.add(lbl3);
		pa3.add(plus);
		pa3.add(minus);
		pa3.add(mult);
		pa3.add(div);
		add(pa3);

		// 4행
		lblResult = new JLabel("결과 : ", JLabel.CENTER);
		JPanel pa4 = new JPanel();
		pa4.add(lblResult);
		add(pa4);

		// 5행
		JPanel pa5 = new JPanel();
		calc = new JButton("계산");
		calc.addActionListener(this);
		clear = new JButton("초기화");
		clear.addActionListener(this);
		exit = new JButton("종료");
		exit.addActionListener(this);

		pa5.add(calc);
		pa5.add(clear);
		pa5.add(exit);
		add(pa5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == calc) {
			// 입력 여부 확인
			double num1 = 0, num2 = 0;
			try {
				if (txtnum1.getText().equals("")) { //
				}
				num1 = Double.parseDouble(txtnum1.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "숫자 1을 입력하세요");
				txtnum1.requestFocus();
				return;
			}

			try {
				if (txtnum2.getText().equals("")) { //
				}
				num2 = Double.parseDouble(txtnum2.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "숫자 2을 입력하세요");
				txtnum2.requestFocus();
				return;
			}

			// 계산
			double calc = 0;
			String calc2 = "";
			if (plus.isSelected()) {
				calc = num1 + num2;
				calc2 = "+";
			} else if (minus.isSelected()) {
				calc = num1 - num2;
				calc2 = "-";
			} else if (mult.isSelected()) {
				calc = num1 * num2;
				calc2 = "*";
			} else if (div.isSelected()) {
				if (num2 == 0) {
					JOptionPane.showMessageDialog(this, "0으로 나눌 수 없습니다.");
					txtnum2.requestFocus();
					return;
				} else {
					calc = num1 / num2;
					calc2 = "/";
				}
			}
			
			//결과 값
			String ss = "결과 : " + txtnum1.getText() + " " + calc2 + txtnum2.getText() + " " + "값은 " + calc + " 입니다";
			lblResult.setText(ss);
		}

		if (e.getSource() == clear) {
			txtnum1.setText("");
			txtnum2.setText("");
			plus.setSelected(true);
			lblResult.setText("결과 : ");
			txtnum1.requestFocus();
		}

		if (e.getSource() == exit) {
			int er = JOptionPane.showConfirmDialog(this, "정말로 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
			if (er == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		new SwingCalc();
	}
}
