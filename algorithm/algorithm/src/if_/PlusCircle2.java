package if_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlusCircle2 { // ���ϱ����Ŭ, �ι�°, ����
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int in = Integer.parseInt(br.readLine()); // 26
		int eq = in; // 26
		
		int cou = 0;
		int sum = 0;
		
		while(true) {
			int a = in / 10; // 2, 6
			int b = in % 10; // 6, 8
			
			int c = a + b; // 2 + 6 = 8, 6 + 8 = 14
			
			c = c % 10; // �� ���� �ƴϾ �������� ����.
			
			sum = (b*10) + c; // 60 + 8 = 68

			in = sum; // 68
			cou++; // 1
			
			if (eq == sum) {
				System.out.println(cou);
				return;
			}
		}
	}
}
