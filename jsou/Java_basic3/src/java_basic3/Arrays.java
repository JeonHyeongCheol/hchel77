package java_basic3;

public class Arrays {
	// Arrays : 배열을 다루는데 유용한 메서드가 정의되어 있음. 같은 기능의 메서드가 배열의 타입만 다르게 오버로딩되어 있어 많지만 실제로 많지 않음.
	
	// 배열의 복사 - copyOf(), copyOfRange()
	// copyOf() : 배열 전체를
	// 			  ex) int[] arr = {0,1,2,3,4};
	//				  int[] Arr2 = arrays.copyOf(arr, 7); -> arr2 = [0,1,2,3,4,0,0]
	// copyOfRange() : 배열의 일부를 복사해서 새로운 배열을 만들어 반환, copyOfRange에 지정된 범위의 끝은 포함되지 않음.
	// 			       ex) int[] arr3 = Arrays.copyOfRange(arr, 2, 4); -> arr3 = [2,3]
	
	// 배열 채우기 - fill(), setAll()
	// fill() : 배열의 모든 요소를 지정된 값으로 채움
	//			ex) int[] arr = new int[5];
	//				Arrays.fill(arr, 9); -> arr = [9,9,9,9,9]
	// setAll() : 배열을 채우는데 사용할 함수형 인터페이스를 매개변수로 받음.
	//			  ex) Array.setAll(arr, () -> (int) (Math.random() * 5 + 1); -> arr = [1,5,2,1,1]
	
	// 배열의 정렬과 검색 - sort(), binarySearch()
	// sort() : 배열을 정렬 할 때
	// binarySearch() : 배열에 저장된 요소를 검색 할 때 사용. 지정된 값이 저장된 위치(index)를 찾아서 반환, 배열이 정렬된 상태여야 올바른 결과 값을 얻음.
	// 순차 검색(linear search) : 배열의 첫 번째 요소부터 순선대로 하나씩 검색. 배열 정렬 필요 없음. 하지만 하나씩 비교하여 속도가 느림.
	// 이진 검색(binary search) : 배열의 검색할 범위를 반복적으로 절반씩 줄여가면서 검색. 검색속도는 상당히 빠름. 단 배열이 정렬 되어 있을 경우.
	
	// 문자열의 비교와 출력 - equals(), toString()
	// toString() : 배열의 모든 요소를 문자열로 편하게 출력, 일차원 배열에만 사용 가능, 다차원에서는 deeptTo stirng() 이용(2,3 차원에서 사용).
	//				int [] arr = {0,1,2,3,4}
	//				int [] arr2d = {{11,12},{21,22}}
	//				ex) System.out.println(Arrays.toString(arr)); -> [0,1,2,3,4]
	//				ex) System.out.println(Arrays.deepToString(arr2D)); -> [[11,12],[21,22]]
	// equals() : 두 배열에 저장된 모든 요소를 비교해서 같으면 true, 다르면 false를 반환, 일차원에서만 사용 가능, 다차원은 deepEquals() 사용.
	//			  2차원 배열을 객체로 선언해놓고 같은 값을 가지고 있을 경우.
	//			  ex) System.out.println(Arrays.equals(str2D, str2D2)); -> false
	//			  ex) System.out.println(Arrays.deepToequals(str2D, str2D2)); -> true
	
	// Comparator와 Comparable : 둘 다 인터페이스로 컬렉션을 정렬하는데 필요한 메서드를 정의
	// Comparable : 기본  정렬 기준을 구현하는데 사용. 기본적으로 오름차순 정렬.
	// Comparator : 기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용. 내림차순, 다른 기준에 의해 정렬하도록 할 때 사용.
	
}
