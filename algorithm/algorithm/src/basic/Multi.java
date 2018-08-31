package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Multi { // °öÇÏ±â

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a [] = br.readLine().split(" ");
		int b = Integer.parseInt(a[0]) * Integer.parseInt(a[1]);
		System.out.println(b);

	}

}
