package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberHap { // 한 줄에 여러숫자를 받아들여 더하기

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		char[] str = new char[n];

		for(int i=0;i<n;i++) {
			System.out.print(br.read()-'0'); 
			// - -'0'는 항상 0 또는 한자리의 자연수를 입력 할 것이라고 가정 했을 때나 쓸수 있는 수식
		}

	}

}


/*
b[] = int.valueOf(br.readLine());

char[] c = b.toCharArray();

for (int i = 0; i < c.length; i++) {
	
}

//String[] c = number.split("(?<=.)");
*/