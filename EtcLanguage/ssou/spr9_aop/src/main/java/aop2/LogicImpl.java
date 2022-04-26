package aop2;

public class LogicImpl implements LogicInter {
	private ArticleInter inter;
	
	public LogicImpl(ArticleInter inter) {
		this.inter = inter;
	}
	
	@Override
	public void Selectdata_process() {
		System.out.println("selectdata_process 수행");
		inter.selectAll();
	}
}
