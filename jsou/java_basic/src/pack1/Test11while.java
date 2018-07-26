package pack1;

public class Test11while {

	public static void main(String[] args) {
		// 반복문 while
		// while 문은 for문과 달리 조건식을 생략 할 수 없음. for문은 조건식 생략 가능.
		int w = 1; //for(변수 = 초기값; 조건; 증감식) {}
		
		while(w <= 5) { //조건이 참인 동안 반복
			System.out.println("w:" + w);
			w += 1; //반복문 탈출을 위한 명령문 필수
		}
		System.out.println("\n 반복문 탈출 후 w:" + w);
		
		System.out.println();
		w = 0;
		while(true) { // 무한루프
			w++;
			if(w == 3) continue;
				System.out.println("w 는" + w);
			if(w == 5) break; // w가 5
			}
		System.out.println("종료");
		
		// do {
		//   조건식이 참일 때 수행하는 문장들
		// while(조건식); <- 세미콜론 잊지 않고 꼭 써야 됨.
	}

}
