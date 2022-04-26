package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Star_4 { // º°Âï±â 4
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int a = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= i - 1; j++) {
				System.out.print(" ");
			}
			for (int k = a - i ; 1 <= k ; k--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}

