package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Div { // ³ª´©±â

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a [] = br.readLine().split(" ");
		
		double b = Double.parseDouble(a[0]) / Double.parseDouble(a[1]);
		
		System.out.println(b);
	}

}
