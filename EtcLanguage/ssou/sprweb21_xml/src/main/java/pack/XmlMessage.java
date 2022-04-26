package pack;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD) // 타입을 만듬.
public class XmlMessage {
	
	private String name;
	private String age;
	
	public XmlMessage(String name, String age) { // 받은 값 사용 할 수 있도록 this로 리턴.
		this.name = name;
		this.age = age;		
	}

	//Xml 만들 때는 get만 있으면 됨.
	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}
	
	
}
