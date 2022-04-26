package advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TestAdvice implements MethodInterceptor{ // AOP Interceptor 할 때는  methodIntercetor를 implements 해줘야 함.
	// MethodIntercepotr는 Around advice를 지원
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable { // 반환시 Object
		
		// Target 메소드 수행 전에 하고 싶은 작업.
		String methodName = invocation.getMethod().getName(); // 핵심 메소드 이름을 얻음.
		System.out.println(methodName + "implements전에 관심사항 수행");
		
		Object object = invocation.proceed(); // 핵심 로직. 새치기 할 메소드를 찾아감. 
		
		// target 메소드 수행 후에 하고 싶은 작업
		System.out.println(methodName + " 수행 후 뭔가를 처리.");
		
		return object;
	}
}
