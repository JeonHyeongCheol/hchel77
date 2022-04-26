package if_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class TestCase { // Test Case, 평균은 넘겠지
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int w = Integer.parseInt(st.nextToken());
		
		while(w-- > 0) {
			double sum = 0;
			st = new StringTokenizer(br.readLine());
			int w2 = Integer.parseInt(st.nextToken());
			int a[] = new int[w2];
			for (int i = 0; i < a.length; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				sum += a[i];
			}
			double avg = sum/w2;
			double cou = 0;
			for (int i = 0; i < a.length; i++) {
				if(avg < a[i]) {
					cou++;
				}
			}
			double result = (cou/w2) * 100;
			bw.write(String.format("%.3f", result) + "%");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}
