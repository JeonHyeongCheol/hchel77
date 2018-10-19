package aop3;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LogicImpl implements LogicInter {
	@Autowired
	@Qualifier("articleDao")
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
