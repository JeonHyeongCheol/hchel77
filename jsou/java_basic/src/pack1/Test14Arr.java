package pack1;

public class Test14Arr {
	public static void main(String[] args) {
		// 다차원 배열 = 2차원배열은 1차원 배열의 묶음
		// 2차원 배열 : [행][열], 3차원 배열 : [면][행][열] 순으로 해줌.
		int su[][] = new int[3][4]; // 3 * 4 2차원 배열
		System.out.println(su.length + " " + su[0].length + " " + su[1].length);
		su[0][0] = 100;
		System.out.println(su[0][0]);
		
		System.out.println();
		
		int num = 10;
		for (int i = 0; i < su.length; i++) { // 행에 대해서 돔
			for (int j = 0; j < su[i].length; j++) { // i보다 작은 동안에 돔
				su[i][j] = num++;
			}
		}
		for (int i = 0; i < su.length; i++) {
			for (int j = 0; j < su[i].length; j++) { // su[i] : i행의 크기 만큼 돌림.
				System.out.print(su[i][j] + " ");	
			}	
			System.out.println();
		}
		
		System.out.println();
		
		// 가변 배열
		int[][] scores = new int[2][];
		scores[0] = new int[2]; // 2차원 배열에 0행에 열 2개를 줌.
		scores[1] = new int[3]; // 2차원 배열에 1행에 열 3개를 줌.
		
		System.out.println(scores.length + " " + scores[0].length + " " + scores[1].length);
		
		System.out.println();
		
		int[][] jum = {{90, 96},{88, 92}}; // 2행 2열 배열
		for (int i = 0; i < jum.length; i++) {
			for (int j = 0; j < jum[i].length; j++) {
				System.out.print(jum[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		int[][] jum2 = {{90, 96},{88, 92, 77}}; // 가변 배열 : 0행 2열, 1행 3열
		for (int i = 0; i < jum.length; i++) {
			for (int j = 0; j < jum2[i].length; j++) {
				System.out.print(jum2[i][j] + " ");
			}
			System.out.println();
		}
		
		// Math.PI //클래스안에 Math.라는 속성에 PI라는 값을 집어 넣어 놓은 것. 괄호() 가 있는 것은 행위.
	}
}