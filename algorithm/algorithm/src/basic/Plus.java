package basic;

import java.util.Scanner;

public class Plus { // ���ϱ�

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("ù��° �� �Է�");
		int a = sc.nextInt();
		System.out.print("�� ��° �� �Է�");
		int b = sc.nextInt();
		System.out.println(a+b);
	}
	
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input=br.readLine().split(" ");
		System.out.println(Integer.parseInt(input[0])+Integer.parseInt(input[1]));
	}
}
 */

}
