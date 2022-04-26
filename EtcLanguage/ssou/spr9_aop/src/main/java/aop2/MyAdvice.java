package aop2;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {
	public Object kbs(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("핵심 메소드 전 작업");
		
		Object object = joinPoint.proceed();
		
		System.out.println("핵심 메소드 후 작업");
		
		return object;
	}
}
