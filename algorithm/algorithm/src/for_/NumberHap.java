package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberHap { // �� �ٿ� �������ڸ� �޾Ƶ鿩 ���ϱ�

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		char[] str = new char[n];

		for(int i=0;i<n;i++) {
			System.out.print(br.read()-'0'); 
			// - -'0'�� �׻� 0 �Ǵ� ���ڸ��� �ڿ����� �Է� �� ���̶�� ���� ���� ���� ���� �ִ� ����
		}

	}

}


/*
b[] = int.valueOf(br.readLine());

char[] c = b.toCharArray();

for (int i = 0; i < c.length; i++) {
	
}

//String[] c = number.split("(?<=.)");
*/