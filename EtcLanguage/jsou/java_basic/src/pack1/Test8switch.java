package pack1;

import java.util.Scanner;

public class Test8switch {

	public static void main(String[] args) {
		// Switch문의 제약 조건
		// Switch문의 조건식 결과는 정수 또는 문자열이어야 함.
		// case문의 값은 정수 상수만 가능하며, 중복되지 않아야 함.
		
		// 조건 판단문
		int nai = 15;
		nai = nai / 10*10; // 나이대 구하기
		System.out.println(nai);
		
		/*		
		if(nai == 20) {
			System.out.println("팔팔해");
		} else if(nai == 30){
			System.out.println("덜 팔팔해");
		}
		*/
		
		switch(nai) { // 조건문 사용 못함. 변수값에따라 "case 변수"로 설정해주면 됨. 문자, 숫자 다 가능. 
					  // switch문에는 조건에 맞는 라인을 수행 후 break를 써줘야 함. 또한 블럭 쓰지 않음
		case 20:
			System.out.println("팔팔해");
			System.out.println("20대");
			break;
		case 30:
			System.out.println("덜 팔팔해");
			System.out.println("30대");
			break;
		case 40:
			System.out.println("비실비실해");
			System.out.println("40대");
			break;
		default:
			System.out.println("기타");
			break;
			
		}
		
		System.out.println("\n");
		
		System.out.println("다음");
		//double time = Math.random(); // Math : 수학적 함수를 사용 할 수 있음. Random : 난수 발생(0~1사이에 랜덤 값 출력)
		//int time = (int)(Math.random() * 10);
		int time = (int)(Math.random() * 4) + 8; // 난수 값에 +8을 더해 출력	
		System.out.println(time);
		
		switch (time) {
		case 8:
			System.out.println("출근하자");
			break;
		case 9:
			System.out.println("회의하자");
			break;
		case 10:
			System.out.println("프로그램 짜자");
			break;
		default:
			System.out.println("명상 시간");
			break;
		}
		
		// 문제) 두 개의 실수와 연산자(+,-,*,/)를 각각 입력 받아 사칙연산 수행
		//
		
		Scanner ma = new Scanner(System.in);
		System.out.print("첫 번째 수를 입력하세요 :");
		double s1 = ma.nextInt();
		System.out.print("두 번째 수를 입력하세요 :");
		double s2 = ma.nextInt();
		System.out.print("연산자를 입력하세요(a(+) s(-) m(*) d(/) :");
		String ms = ma.next();
		switch (ms) {
		case "a":
			System.out.println(s1 + s2);
			break;
		case "s":
			System.out.println(s1 - s2);
			break;
		case "m":
			System.out.println(s1 * s2);
			break;
		case "d":
			System.out.println(s1 / s2);
			break;
		}
		
	}

}
