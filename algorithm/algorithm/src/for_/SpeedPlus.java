package for_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SpeedPlus { // ���� A+B, BufferedReder, Writer, StringTokenizer
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int arr[] = new int[T];
		
			for (int i = 0; i < T; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				arr[i] = A + B;
			}
			
			for(int result : arr) {
				bw.write(String.valueOf(result)); 
				// StringTokenizer�� ��ü�� ���� �� ��� �߱� ������  valueOf ����Ÿ���� ��� �Ѵ�.
				// parseInt�� ����Ÿ���� �⺻ �ڷ���.
				bw.newLine();
			}
		bw.flush();
	}
}

/*
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		while (testcase-- > 0) {
			String str1[] = br.readLine().split(" ");
			int A = Integer.parseInt(str1[0]);
			int B = Integer.parseInt(str1[1]);
			bw.write((A + B) + "\n");
			if (testcase % 1000 == 0)
				bw.flush();
		}
		bw.flush();
	}
}
*/
