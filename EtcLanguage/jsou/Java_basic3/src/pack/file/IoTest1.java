
package pack.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class IoTest1 {
	public static void main(String[] args) throws IOException { // 예외처리시켜줘야 함.
		/*
		// 1 byte 단위의 입출력
		System.out.println("1바이트 입력 : ");
		int a = System.in.read(); // 외부 장치를 쓰고 있기 때문에 예외 처리를 시켜야 함. 1 바이트 입력 명령어. 캐릭터는 될 수 없음. 문자는 2바이트 단위.
		System.out.print("입력값 a : " + a + " " + (char)a); // 입력 받은 값 a는 정수 이기 때문에 들어오는 값을 정수로 바꿔줌.
		 */
		
		
		
		// 문자열 출력
		OutputStreamWriter os = new OutputStreamWriter(System.out); // System.out는 PrintStream임
		/*
		System.out.println();
		byte b = 97;
		os.write(b); // 출력시키기
		os.flush(); // 출력을 한 후 비움
		*/
		
		// Buffer 보조스트림 : 입출력 성능 향상
		/*
		BufferedWriter bw = new BufferedWriter(os,1024); // 1kbyte 단위로 저장한다는 얘기
		PrintWriter out = new PrintWriter(bw);
		out.println(123);
		out.print("오늘은 금요일");
		out.close();
		bw.close(); // 지원 해제 : 메모리 반납 - GC(가비지 컬렉터)쓰레기 값 
		*/
		
		File f = new File("c:/work/iotest.txt");
		FileWriter fw = new FileWriter(f); // 파일 쓰기
		BufferedWriter bw2 = new BufferedWriter(fw, 1024); // 안주면 기본으로 1024단위로
		PrintWriter out2 = new PrintWriter(bw2); // 입력하기
		out2.println(1234);
		out2.println("문자열이 출력됨");
		out2.println("Stream을 사용함");
		out2.close();
		bw2.close();
		fw.close();
		
		System.out.println("파일 출력 성공");
	}
}
