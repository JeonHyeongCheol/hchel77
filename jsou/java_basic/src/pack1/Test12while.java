package pack1;

public class Test12while {

	public static void main(String[] args) {
		// while 연습문제
		// 문제1) 1 ~ 100 사이의 정수 중 3의 배수이지만 2의 배수가 아닌 수를 출력하고 그 합과 갯수 출력
		
		int w = 1;
		int sum = 0, count = 0;
		while(w <= 100) {
			if(w % 3 == 0 && w % 2 != 0) {
					sum += w;
					count += 1;
					System.out.print(w + " ");
			}
			w++;
		}
		System.out.println("3의배수이지만 2의 배수가 아닌 수의 합 :" + sum + ", 갯수 :" + count);
		
		System.out.println("\n");
		
		// 문제2) -1, 3, -5, 7, -9, 11 ~ 99 까지의 합 출력
		int e = 1;
		int c = 1;
		int sum2 = 0;
		int sw = 0;
		while(e <= 99) {
			if (sw == 0) {
				c = e * (-1);
				sw = 1;	
			} else if (sw == 1){
				c = e * 1;
				sw = 0;
				
			}
			sum2 += c;
			e += 2;
		}
		System.out.println("-1에서 ~ 99까지의 합은: " + sum2);

		System.out.println("\n"); 
		
		// 문제3) 1 ~ 1000 사이의 소수와 그 갯수를 출력
		// 소수 : 1보다 크고, 자신 이외의 다른 수로는 나누어 떨어지지 않는 수
		
		
		int i = 1; 
		int ssn = 0;
		while(i <= 1000) {
			int j = 2;
			while(j <= i) {
				if(i % j == 0) { // i 값과 j 값이 나누어 떨어지고 나머지가 0 일때
					if(i == j) {// i값과 j값이 같으면
						System.out.print(i + " ");
						ssn += 1;
					}
					else
						break;
				}
				j++;
			}
			i++;
		}
		System.out.println();
		System.out.print("소수의 갯수 : " + ssn);
	}
}