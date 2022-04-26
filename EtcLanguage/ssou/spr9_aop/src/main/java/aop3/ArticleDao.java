package aop3;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository // DB관련.
public class ArticleDao implements ArticleInter{
	
	@Override
	public void selectAll() {
		System.out.println("상품 테이블 전체자료 읽기");
	}
}
