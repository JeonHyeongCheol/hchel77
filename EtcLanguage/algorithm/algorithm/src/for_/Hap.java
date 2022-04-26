package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hap { // 합 구하기
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int a = Integer.parseInt(br.readLine());
		int hap = 0;
		
		for (int i = 1; i <= a; i++) {
			hap += i;
		}
		
		System.out.println(hap);
	}
}
