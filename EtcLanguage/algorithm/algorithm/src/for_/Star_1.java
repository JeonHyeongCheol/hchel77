package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Star_1 { // 별찍기 1, 규칙찾기

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int s = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= s; i++) {
			for (int j = 1; j <= i ; j++) {
					System.out.print("*");
			}
			System.out.println();
		}
	}

}
