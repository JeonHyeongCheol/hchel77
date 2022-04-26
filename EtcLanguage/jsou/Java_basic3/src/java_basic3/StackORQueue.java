package java_basic3;

public class StackORQueue {
	// Stack(스택) : 마지막에 저장한 데이터를 가장 먼저 꺼내게 되는 LIFO(Last In First Out)구조
	// Queue(큐) : 처음에 저장한 데이터를 가장 먼저 꺼내게 되는 (First In First Out)구조
	
	// Stack 구현 : stack은 컬렉션 프레임웍 이전부터 존재하던 것이기 때문에 arrayList가 아닌 vector로 부터 상속 받아 구현함.
	
	// 스택과 큐의 활용
	// 스택의 활용 : 수식 계산, 수식 괄호 검사, 워드프로세서의 undo/redo, 웹브라우저의 뒤로/앞으로
	// 큐의 활용 : 최근사용문서, 인쇄작업 대기목록, 버퍼(buffer)
	
	// PriorityQueue : Queue 인터페이스의 구현체 중의 하나로, 저장한 순서에 관계없이 우선순위(priority)가 높은 것부터 꺼내게 된다는 특징이 있음.
	//				   null은 저장 할 수 없으며, null을 저장하면 nullPointerExcepiton 발생.
	//				     저장공간으로 배열을 사용, 각 요소를 '힙(heap)'이라는 자료구조의 형태로 저장.
	//				     힙 : 이진 트리의 한 종류로 가장 큰 값이나 가장 작은 값을 빠르게 찾을 수 있다는 특징이 있음.
	
	// Deque(Double-Ended Queue) : Queue의 변형, 한쪽 끝으로만 추가/삭제 할 수 있는 Queue와 달리 Deque(덱 또는 디큐)은 양쪽 끝에 추가/삭제 가능.
	// 							   Deque의 조상은 Queue, 구현체로는 ArrayDeque과 LinkedList 등이 있음.
	//							     덱은 스택과 큐를 하나로 합쳐놓은 것과 같으며 스택으로 사용 할 수 있고, 큐로도 사용 할 수 있음.
}
