package beanpack;

public class Para1Class {
	private String msg; // 자동화 하기 위해서는 클래스에 변수명과 html에 넘어오는 name이 같아야 함.
	
	// 자동화를 위해 set, get을 써야 함.
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
}
