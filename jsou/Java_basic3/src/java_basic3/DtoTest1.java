package java_basic3;

import java.util.ArrayList;

public class DtoTest1 {
	
	// DTO(Data Transfer Object) 는 계층간 데이터 교환을 위한 자바빈즈를 의미합니다. 
	// 여기서 말하는 계층간의 의미는 Controller, View, Business Layer, Persistent Layer 등을 말하며 각 계층간 데이터 교환을 위한 객체를 의미합니다. 
	// DTO는 로직을 가지지 않는 순수한 데이터 객체이고 getter, setter 메소드만 가진 클래스를 의미합니다


	ArrayList<StudentDto> list = new ArrayList<>(); // studentDto 타입을 저장하기위한 arrlist.
	
	public void aa() {
		String persons[] = new String[3];
		persons[0] = "홍길동";
		persons[1] = "신길동";
		persons[2] = "나길동";
		
		for(String s:persons) {
			System.out.println(s);
		}
	}
	
	public void insertData() { // 여러명의 학생정보를 기억
		StudentDto dto = null;
		
		dto = new StudentDto(); // 이렇게 계속 할 경우 덮어쓰기가 되버림.
		dto.setHakbun("ks1000");
		dto.setIrum("손오공");
		dto.setJumsu(90);
		list.add(dto); // 이렇게 줌으로써 덮어쓰기가 되지 않고 하나씩 추가 됨
		
		dto = new StudentDto(); 
		dto.setHakbun("ks1001");
		dto.setIrum("사오정");
		dto.setJumsu(88);
		list.add(dto);
		
		dto = new StudentDto(); 
		dto.setHakbun("ks1002");
		dto.setIrum("저팔계");
		dto.setJumsu(78);
		list.add(dto);
	}
	
	public void dispData() {
		System.out.println("저장된 학생수는 " + list.size() + "명");
		for(int k=0;k < list.size(); k++) {
			//ArrayList list = new ArrayList<>(); // 6번째 줄에 타입이 object이면 밑에서 출력 시킬 때 에러가남.
			//StudentDto dto = (StudentDto)list.get(k); // 그래서 선언 객체 앞에 캐스팅을 해주어야 함(형변환).
			StudentDto dto = list.get(k); // 6번째 줄에서 타입을 studentdto로 정해놨기 때문에 오류가 발생하지 않음.
			System.out.println(dto.getHakbun() + " " + dto.getIrum() + " " + dto.getJumsu());
		}
	}
	
	public static void main(String[] args) {
		DtoTest1 test1 = new DtoTest1();
		test1.aa();
		test1.insertData();
		test1.dispData();
		
	}

}
