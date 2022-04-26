package pack4;

public class PolyProduct {  //가전제품 원형 클레스
	private int valumn = 0;
	
	public void volumnControl() {               //추상적이다.  추상클레스
		//내용은 비워둠 - 오버라이딩된 매소드에서 구체적인 내용을 적어줌.
	}
	
	public int getValumn() {
		return valumn;
	}
	public void setValumn(int valumn) {
		this.valumn = valumn;
	}
}
