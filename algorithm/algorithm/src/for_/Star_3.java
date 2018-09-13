package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Star_3 { // º°Âï±â 3

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int a = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < a ; i++) {
			for (int j = a-i; 1 <= j; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
/*
for (int j = i; j <= i; j++) {
	System.out.print(" ");
}
*/