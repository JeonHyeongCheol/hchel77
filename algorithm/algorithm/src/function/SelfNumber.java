package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SelfNumber {
	public static void func() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String in = br.readLine();
		int fr = Integer.parseInt(in);
		
		for (int i = 0; i < 100; i++) {
			
			int a = fr / 10;
			int b = fr % 10; 
			
			int sum = fr + a + b;
			
			fr = sum;
			System.out.println(sum);
		}
	}
	
	public static void main(String[] args) throws IOException {
		func();
	}
}
