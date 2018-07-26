package pack1;

import java.util.Scanner;

public class Test8switch2 {

	public static void main(String[] args) {
		// Switch 문도 중첩으로 사용 가능. case를 겹쳐서 사용 가능함. 밑에 라인 예제 참조.
		/*
		switch (jik) {
		case "사원": case "대리":
			msg = "열심히";
			break;
		case "과장":
			msg = "우수해";
			break;
		*/
		
		
		String jik = "과장";
		String msg = "기타";
		
		switch (jik) {
		case "사원":
			msg = "열심히";
			break;
		case "대리":

		case "과장":
			msg = "우수해";
			break;
		default:
			System.out.println("그 외 직급");
			break;
		}
		System.out.println(msg);
		
		//키보드로 부터 년과 월을 입력받아 해당년월의 날수를 출력
		String str = "평년";
		int year, month, nalsu = 28;
		Scanner input = new Scanner(System.in);
		System.out.println("년도 :");
		year = input.nextInt();
		System.out.println("월 :");
		month = input.nextInt();
		
		if(month < 1 || month > 12) {// 1월부터 12월까지만 허용. 아니면 거짓
			System.out.println("월은 1 ~ 12 사이만 허용!");
			System.exit(0);				
			}
		
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			int imsi = 7; // 이 if문 블록 안에서만 사용 가능
			nalsu = 29;
			str = "윤년";
		}
		
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			nalsu = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			nalsu = 30;
			break;
		case 2:
			
			break;	
		default:
			break;
		}
		//배열을 쓰게되면 거의 for문을 쓴다고 보면 됨.
		System.out.println(year + "년 " + month + "월은 " + nalsu + " " +str);
		
		
	}

}
