package aop2;

public class ArticleDao implements ArticleInter{
	
	
	@Override
	public void selectAll() {
		System.out.println("상품 테이블 전체자료 읽기");
	}
}
