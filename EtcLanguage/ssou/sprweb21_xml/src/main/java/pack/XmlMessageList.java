package pack;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="msg-list") // Root Elemnet 이름
public class XmlMessageList {
	@XmlElement(name="msg") // Element 이름
	private List<XmlMessage> messages;
	
	public XmlMessageList() {
		
	}
	
	public XmlMessageList(List<XmlMessage> messages) { // 받은 값을 Xml로 만들어 줌.
		this.messages = messages;
	}
	
	public List<XmlMessage> getMessage() { // 받은 값 리턴 해줌.
		 return messages; 
	}
}
