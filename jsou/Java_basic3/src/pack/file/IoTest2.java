package pack.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Scanner;

public class IoTest2 {
	public static void main(String[] args) throws IOException{
		
		//<InputStreamReader>
		//- 입력 데이터를 Character 단위로 읽는다.

		//<BufferedReader>
		//- 버퍼를 두고 테이터를 사용자 요청때마다 읽는 것이 아니라 일정량을 한번에 
		//  읽은 후에 버퍼에 보관한다. 사용자 요청시 버퍼에서 읽어온다.
		/*
		
		// Console을 통한 입력
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);
		System.out.print("이름 입력 : ");
		String irum = br.readLine();
		System.out.print("나이 입력 : ");
		String nai = br.readLine();
		System.out.println("이름은 " + irum + ", 나이는" + nai );
		br.close();
		in.close();
		*/
		
		// 스캐너 사용
		/*
		Scanner scanner = new Scanner(System.in); 
		System.out.print("이름 입력 : ");
		String ir = scanner.nextLine(); // Scanner.next() 는 연속된 문자열만 받을 수 있음. nextLine()는 그 라인에 있는 것을 읽어줌
		System.out.print("나이 입력 : ");
		//int nai = scanner.nextInt();
		double nai = scanner.nextDouble(); // 실수형으로 받을 수도 있음.
		System.out.println("이름은 " + ir + ", 나이는" + nai);
		*/
		
		System.out.println("파일 읽기");
		File f = new File("c:\\work\\iotest.txt"); // 불러올 파일 경로 설정
		FileReader fr = new FileReader(f); // 파일을 읽음
		BufferedReader br = new BufferedReader(fr); // 가져온 자료 버퍼에 저장
		while(true) {
			String ss = br.readLine();
			if(ss == null) break;
			System.out.println();
			
			
		}
		br.close();
		fr.close();
	}
}
