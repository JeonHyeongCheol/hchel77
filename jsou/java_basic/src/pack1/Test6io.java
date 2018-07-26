package pack1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;

public class Test6io {

	public static void main(String[] args) throws Exception { 
		//String[]에 args는 실행 시 java 파일명
		//(ex. test6io 변수값(args) 변수값(args) ... 배열값이 비어있기 때문에 계속 받을 수 있음)
		// 프로그램 진행 중 외부에서 값 얻기
		if(args.length == 0) {// length : 크기,길이 
			System.out.println("외부에서 값 받기 실패"); // args에 값이 없기 때문에 실패
			System.exit(1); // 응용프로그램 강제 종료. 강제 종료 시키기 때문에 다음 라인으로 넘어가지 않음.
		}
		
		System.out.println(args[0]);
/*		
		System.out.println("\n키보드로 값 얻기");
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		// 키보드로 값 받기. BufferedReader 변수이름 = new BufferedReader();으로 쓸 것. 
		// 나중에 클래스 이용시 공란으로 받는 값이 많기 때문에 습관화 할 것.
		System.out.print("이름입력:");
		String irum = buf.readLine(); //buf는 값 받기, readLine 한 줄씩 받아 들임
		System.out.print("나이입력:");
		String nai = buf.readLine();
		System.out.println("이름은 " + irum + ", 나이는 " + nai);
		buf.close(); // 자원 해제. 가비지 컬렉터가 자동으로 쓰지 않는 값을 버리지만 미리 해지하고 싶다면 close() 명령어 사용.
*/		
		System.out.println("\n키보드로 값 얻기2");
		Scanner sc = new Scanner(System.in);
		System.out.print("상품명:");
		String product = sc.next();
		System.out.print("가격:");
		int price = sc.nextInt();
		System.out.println("상품명은 " + product + ", 가격은 " + price);
		sc.close();
		
		System.out.println("프로그램 정상 종료");
	}

}
