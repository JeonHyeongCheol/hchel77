package pack1;

public class Test3 {

	public static void main(String[] args) {
		// 관계, 논리, 기타 연산자
		int a = 5;
		System.out.println(a > 3); // println에서 결과 값을 나타 낼 수 있음. printf는 불가능
		System.out.println(a <= 3);
		System.out.println(a == 3);
		System.out.println(a != 3);
		
		System.out.println();
		int b = 10;
		System.out.println(a > 3 && b <=10); // 논리곱(AND). 첫번째와 두번째 조건을 만족해야지 참, 하나라도 거짓이면 거짓으로 출력.
		System.out.println(a >= 3 && b ==10); // 위에와 같은 의미
		
		System.out.println(b > 6 || b < 10); // 논리합(OR) 둘 중에 하나라도 참이 참. 둘 다 거짓일 때만 거짓.
		System.out.println(b <= 6 || b == 3+7); // 위에와 같은 의미. 우선순위 (소괄호) > 산술 > 관계 > 논리 > =(치환)
		// 비트 전환 연산자 (~) : ~x 했을 때 x가 1이면 반대로 0이 되는 것을 마람. 논리 부정연산자 (!)와 유사.
		// 복합 연산자 : x = x + 3 은  x += 3과 동일 함. 
		
		System.out.println();
		//int ii = 8, ij;
		//System.out.println(ii + " " + ij); // ij에 초기값 설정을 하지 않았기 때문에 추력 될 수 없음. 초기값 설정을 해주어야 함. 
		
		
		int ii = 8, ij = 0;
		System.out.println(ii + " " + ij);
		System.out.println("ii:" + ii + " " + Integer.toBinaryString(ii)); // Integer.toBinaryString()는 2진수로 나타내준 것
		
		// 문자열의 비교 : equals() 사용.
		
		// shift 연산자
		ij = ii << 1; // 왼쪽(<-)으로 밀어버리기. >>(->)는 오른쪽으로 밀어버리기
		System.out.println("ij:" + ij + " " + Integer.toBinaryString(ij)); 
		
		ij = ii >> 2; // >>(->)는 오른쪽으로 밀어버리기
		System.out.println("ij:" + ij + " " + Integer.toBinaryString(ij)); 
		
		ij = ii >>> 2; // 왼쪽(<-)으로 밀어버리기. >>(->)는 오른쪽으로 밀어버리기
		System.out.println("ij:" + ij + " " + Integer.toBinaryString(ij));
		
		System.out.println();
		// 삼항연산자
		int result = (ii <= 5)?100:100 + 20; // (조건)?참값:거짓값
		System.out.println("result:" + result);
		
		System.out.println();
		
		abc(); // 호출
		System.out.println("이런저런 일을 하다가");
		abc();
		System.out.println("뭔가를 하다가");
		def(10); // 밑에 메소드에서 타입이 int이기 때문에 실수를 넣으면 받아들이지 못함.
		System.out.println("****");
		def((int)12.3);
		abc();

	}
	
	public static void abc() { // 반복적으로 사용 할 때 메소드를 하나 더 만들어서 재사용. 가독성이 있음.
		System.out.println("unit 수행");
	}
	
	public static void def(int arg) { // 소괄호() 안에 값을 줘야지 실행시켜줌(인자, parameter, argument라 함). void는 빈손으로 돌아 간다는 뜻. 
		System.out.println("def unit 수행");
		System.out.println("arg:" + arg);
		
	}

}