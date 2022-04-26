package aop1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	private MyAspect myAspect;
	
	public void setMyAspect(MyAspect myAspect) {
		this.myAspect = myAspect;
	}
	
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable { // Target 메소드 정의 후 intercept.
		Object object = null;
		
		myAspect.myLogin();
		object = joinPoint.proceed(); // 핵심 메소드.
		myAspect.myLogout();
		
		return object;
	}
	
	public Object logging2(ProceedingJoinPoint joinPoint) throws Throwable { // Target 메소드 정의 후 intercept.
		Object object = null;
		
		myAspect.mySecurity();
		object = joinPoint.proceed(); // 핵심 메소드.
		
		return object;
	}

}
