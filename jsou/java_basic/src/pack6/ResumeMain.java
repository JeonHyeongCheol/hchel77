package pack6;

public class ResumeMain {
	public static void main(String[] args) {
		
		R_Tom tom = new R_Tom();
		tom.setIrum("톰");
		tom.setPhone("111-1111");
		tom.setJuso("강남구 테헤란로 111");
		tom.print();
		
		System.out.println();
		tom.playJava(false);
		// tom.changeData(); // static이기 때문에 하지 못하도록 막아 놓음
		Resume.changeData();
		
		System.out.println("-----------------");
		
		R_James james = new R_James();
		james.setIrum("제임스");
		james.setPhone("222-2222");
		james.setSkill("웹 전문가");
		james.print();
		
		System.out.println("\n인사 담당자가 이력서 보기-----");
		
		Resume[] resBox = new Resume[2]; // 배열크기 2
		resBox[0] = tom;
		resBox[1] = james;
		for(Resume kbs:resBox) { // kbs는 변수
			kbs.print();
			System.out.println();
			System.out.println("다음 지원자 정보***");
		}
		
	}
}
