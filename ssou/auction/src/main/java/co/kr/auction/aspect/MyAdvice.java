package co.kr.auction.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class MyAdvice {
	@Autowired
	private LoginClass loginClass;

	@Around("execution(public * aop* (..))")
	public Object Process(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		
		//반복문으로 response, request 값 저장. 
		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof HttpServletResponse) {
				response = (HttpServletResponse) obj;
			}

			if (obj instanceof HttpServletRequest) {
				request = (HttpServletRequest) obj;
			}
		}

		// 세션 체크 후 로그인하지 않은 경우 로그인 창으로 이동
		if (loginClass.loginCheck(request, response)) {
			//return "redirect:/" //핵심메소드 리턴 타입이 String이면 ;
			return new ModelAndView("member/login"); //핵심메소드 리턴 타입이 ModelandView이면
			//return "login";
		}else {
			Object object = joinPoint.proceed();
			return object;			
		}

	}
}
