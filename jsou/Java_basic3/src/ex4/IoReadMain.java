package ex4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class IoReadMain {
	public static void main(String[] args) throws IOException {
		int count = 0;
		
		File f = new File("c:\\work\\festival.ttl"); // 불러올 파일 경로 설정
		FileReader fr = new FileReader(f); // 파일을 읽음
		BufferedReader br = new BufferedReader(fr); // 가져온 자료 버퍼에 저장
		System.out.println("학교명" +  "\t\t"  +"학구ID" + "\t\t" + "학교ID" + "\t\t"+ "학교급구분" + "\t\t" + "시도교육청코드");
		while(true) {
			String ss = br.readLine();
			if(ss == null || count >= 100) break;
			
			StringTokenizer tok = new StringTokenizer(ss,","); 	//String[]tok = ss.split(","); // 요렇게도 사용 가능. 밑에 코드 같이 있음
			String hakguid = tok.nextToken(); // 학구ID
			String hakid = tok.nextToken(); // 학교ID
			String scname= tok.nextToken(); // 학교명
			String hakr= tok.nextToken(); // 학교급구분
			String code= tok.nextToken(); // 시도교육청코드
			System.out.println(scname + "\t"+hakguid + "\t" + hakid + "\t" + hakr+ "\t\t" + code);
			
			
			/*
			String[]tok = ss.split(",");
			System.out.println("학구 ID : "+tok[0]);
			System.out.println("학교 ID : "+tok[1]);
			System.out.println("학 교 명 : "+tok[2]);
			System.out.println("학교급구분 : "+tok[3]);
			System.out.println("시도교육청코드 : "+tok[4]);
			System.out.println("*************************");
			*/
			count++;
		}
		br.close();
		fr.close();
		
	}
}
