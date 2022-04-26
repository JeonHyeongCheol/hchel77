package ex2;

import java.util.ArrayList;
import java.util.Scanner;

public class StudDtoMain {
	ArrayList<StudDto> list = new ArrayList<>();
	String ny2;
	
	public void insert() {
		StudDto dto = null;
		Scanner i = new Scanner(System.in);
		
		while(true) {
			dto = new StudDto();
			System.out.print("이름을 입력하세요 : ");
			String ir = i.next(); // nextLine : 라인은 전체를 받아들임.
			dto.setIrum(ir); // dto.setIrum(i.next()) <= 요것도 가능. 스캐너 바로 사용 가능
			
			System.out.print("국어점수를 입력하세요 : ");
			int ko = i.nextInt();
			dto.setKor(ko);
			
			System.out.print("영어점수를 입력하세요 : ");
			int en = i.nextInt();
			dto.setEng(en);
			
			dto.avg();
			list.add(dto);
			
			System.out.print("계속 하시겠습니까?(y/n)");
			String ny2 = i.next();
			
			if(ny2.equals("n"))
				break;
		} 
		
		
	}
	
	public void dispData() {
		System.out.println("이름 국어 영어 총점");
		for(int k=0;k < list.size(); k++) {
			//ArrayList list = new ArrayList<>(); // 6번째 줄에 타입이 object이면 밑에서 출력 시킬 때 에러가남.
			//StudentDto dto = (StudentDto)list.get(k); // 그래서 선언 객체 앞에 캐스팅을 해주어야 함(형변환).
			StudDto dto = list.get(k); // 6번째 줄에서 타입을 studentdto로 정해놨기 때문에 오류가 발생하지 않음.
			System.out.println(dto.getIrum() + " " + dto.getKor() + " " + dto.getEng() + " " + dto.avg());
		}
		System.out.println("응시인원 " + list.size() + "명");
	}
	
	public static void main(String[] args) {
		StudDtoMain ma = new StudDtoMain();
		ma.insert();
		ma.dispData();
		
	}
}
