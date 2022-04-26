package anno1_required;

import org.springframework.beans.factory.annotation.Required;

public class Abc {
	private int number;

	@Required // 반드시 Setter를 줘야된다는 Annotation. Xml에서도 설정 해줘야 수행이 됨.
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		
		return "number : " + number;
	}
}
