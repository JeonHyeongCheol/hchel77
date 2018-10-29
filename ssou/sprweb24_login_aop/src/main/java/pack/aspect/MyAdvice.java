package pack.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice {
	@Autowired
	private LoginClass loginClass;
	
	@Around("execution(* getList*(..))")
	public Object aopProcess(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = null; // 값이 없을 때 수행 할 수 없도록 기본적으로 Null을 줌.
		HttpServletResponse response = null;
		
		// get args가 있을 때 만 돔!
		for(Object obj:joinPoint.getArgs()) { // 넘어오는 값을 읽을 수 있음. no, name, getList
			if(obj instanceof HttpServletResponse) { // http 객체 만듬.
				response = (HttpServletResponse) obj;
			}
			
			if(obj instanceof HttpServletRequest) {
				request = (HttpServletRequest) obj;
			}
		}
		
		// 세션 체크 후 로그인하지 않은 경우 로그인 창으로 이동.
		if(loginClass.loginCheck(request, response)) { // 가서 Null 값이면 login창으로 이동되게 loginClass에서 설정 되어 있음.
			return null;
		}
		
		Object object = joinPoint.proceed(); // 값이 다 있을 경우 리스트를 그냥 뿌려줌.
		return object;
	}
}
