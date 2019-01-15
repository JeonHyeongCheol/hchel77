package co.kr.auction.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
public class XmlContent {

	@XmlElement(name = "name")	
	private List<String> list;
	
	public XmlContent() {
		// TODO Auto-generated constructor stub
	}
	public XmlContent(List<String> list) {
		this.list = list;
	}
	
	public List<String> getList() {
		return list;
	}
}
