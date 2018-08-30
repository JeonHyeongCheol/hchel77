package beanpack;

public class ExamProcess {
	private FormBean formBean; // formbean 클래스를 변수로 지정.
	
	public void setFormBean(FormBean formBean) { // setter를 이용해 formbean에 있는 값 받아옴.
		this.formBean = formBean;
	}
	
	public int getTot() {
		return formBean.getKor() + formBean.getEng(); // formbean 클래스에 있는 점수를 가져와 총점 출력.
	}
	
	public double getAvg() { // getTot() 메소드를 가져와 평균 출력.
		return getTot() / 2.0;
	}
}
