package pack2;

import java.util.Calendar; // 캘린더에 대한 라이브러리 추가.

public class MySingletonMain {
	public static void main(String[] args) {
			MySingleton s1 = new MySingleton(); // 새로운 객체 선언
			MySingleton s2 = new MySingleton(); // 새로운 객체 선언

			System.out.println(s1 + " " + s2); // 아무것도 없기 때문에 주소 값만 찍어냄.
			System.out.println();
			MySingleton s3 = MySingleton.getInstance(); // static 이기 때문에 바로 부름.
			MySingleton s4 = MySingleton.getInstance();
			System.out.println(s3+" "+s4); // 아무것도 없기 때문에 주소 값만 찍어냄.
			System.out.println();
			
			Calendar calendar = Calendar.getInstance();
			int y = calendar.get(calendar.YEAR); // 현 년도에 대한 값을 받음.
			System.out.println("년도는 " + y); // 출력
			
			Calendar calendar2 = Calendar.getInstance();
			int y2 = calendar2.get(calendar.YEAR); // 현 년도에 대한 값.
			int m = calendar2.get(calendar.MONTH) + 1; // 현 개월에 대한 값. Month 는 항상 +1 해줘야함 (0부터 시작함) 
			System.out.println("년도는 " + y2 + " " + "달은 " + m + "월" );
			
			
	}

}
