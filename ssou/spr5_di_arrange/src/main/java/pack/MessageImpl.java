package pack;

import pack2.OutfileInter;

public class MessageImpl implements MessageInter{
	
	// 생성자 연습용
	private String name1, name2; 
	private int year;
	private MyInfo myinfo;
	
	// Setter 연습용
	private String spec; 
	private OutfileInter outfileInter; // pack2에 있는 interface import.
		
	public MessageImpl(String name1, String name2, int year, MyInfo myinfo) { // 오버로딩
		// constructor injection(생성자 인젝션)
		this.name1 = name1;
		this.name2 = name2;
		this.year = year;
		this.myinfo = myinfo;
	}
	
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public void setOutfileInter(OutfileInter outfileInter) {
		this.outfileInter = outfileInter;
	}
	
	@Override
	public void sayHi() { // 오버라이딩
		// 출력담당
		String msg = name1 + " " + name2 + "\n" + (year + 18) + "년";
		msg += "\n" + myinfo.myData();
		msg += "\n" + spec;
		System.out.println(msg);
		outfileInter.outFileProcess(msg);
	}
}
