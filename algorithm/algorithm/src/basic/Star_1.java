package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Star_1 { // º°Âï±â 1

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
