package aop3;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP 들어갈 작업.
@Component
public class MyAdvice {
	@Around("execution(public * aop3..*(..))") 
	// aop3 패키지 안에 있는 모든 메소드들에게 건다는 것. 일부 메소드할경우 메소드명을 쓰거나 일부를 써야 함.
	public Object kbs(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("핵심 메소드 전 작업");
		
		// 로그인 작업
		System.out.println("input id : ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		
		if(!id.equalsIgnoreCase("aa")) {
			System.out.println("ID 불일치! 작업을 종료합니다.");
			return null;
		} else {
			Object object = joinPoint.proceed();
			
			System.out.println("핵심 메소드 후 작업");			
			
			return object;
			
		}
		
		
		// @Before @After..
	}
}
