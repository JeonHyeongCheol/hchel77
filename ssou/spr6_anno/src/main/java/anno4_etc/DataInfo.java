package anno4_etc;

import org.springframework.stereotype.Component;

@Component
public class DataInfo {
	private String name = "홍길동";
	private String part = "영업부";
	public String job = "프로그래머";
	
	public String getName() {
		return name;
	}
	
	public String getPart() {
		return part;
	}
	

}
