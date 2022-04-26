package pack1;

public class Test10 {

	public static void main(String[] args) {
		// for 계속
		/*
			for( (객체)타입  변수명 : 배열 또는 컬랙션 {
					반복할 문장
			}
			
			ex)
			for( int  tmp : arr ) { // 향상된 for문
					System.out.println(tmp);
			}
			
		*/
		for(int i = 1; i <= 10; i++) {
			if(i == 3) continue; // continue : 3일 때 출력하지 않고 지나 간다는 뜻.
			System.out.println(i);
			// if(i == 5) break; // break : 5일 때 for문(반복문) 탈출 한다는 뜻
			// if(i == 5) System.exit(1); // 응용프로그램 종료. 마지막라인 안찍고 그냥 강제종료.
			if(i == 5) return; // return : 메소드 탈출
		}
	
		System.out.println();
		/*
		int kk = 0;
		for(;;) { // 무한루프
			kk++;
			System.out.println("출력");
			if(kk == 5) break;
		} 
		 */
		
		System.out.println(); // 밑에있는 방법은 잘 안쓰이지만 알고는 있을 것.
		aa:for (int i = 0; i <= 3; i++) {
			bb:for (int j = 0; j <= 5; j++) {
				System.out.println(i + " " + j + " ");
				//if(j == 3) continue;
				//if(j == 3) continue aa;// aa로 돌아감
				if(j == 3) break aa;
			}
			System.out.println("------------");
			
		}
		
		System.out.println("종료");
		
	
	}

} // return 썼을 때 이쪽 괄호(main 메소드) 탈출.
