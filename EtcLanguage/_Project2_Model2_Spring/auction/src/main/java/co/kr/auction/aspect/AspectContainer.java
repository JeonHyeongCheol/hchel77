package co.kr.auction.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.kr.auction.model.ProductDaoInter;

@Aspect
@Component
public class AspectContainer {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDaoInter productDaoInter;
	
	@Around("execution(* co.kr.auction.controller.*.*(..))") // 전체 함수 입력 요망.
	public Object interception(ProceedingJoinPoint joinpoint) throws Throwable{
		
		// 상품 자동 업데이트
		productDaoInter.productupdate();

		Object object = joinpoint.proceed();
		
		return object;
	}

}
