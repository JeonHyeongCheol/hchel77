package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input { // 입력 값 받아서 출력 하기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String a = br.readLine();
		String b = br.readLine();

		int c = Integer.parseInt(a) + Integer.parseInt(b);

		System.out.println(c);

	}
}
