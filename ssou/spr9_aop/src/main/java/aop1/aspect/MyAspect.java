package aop1.aspect;

public class MyAspect {
	public void myLogin() {
		System.out.println("핵심 메소드 수행 전에 로그인 작업을 함");
	}
	
	public void myLogout() {
		System.out.println("핵심 메소드 수행 후에 로그아웃 함");
	}
	
	public void mySecurity() {
		System.out.println("핵심 메소드 수행전에 로그인 보안 설정을 함");
	}
}
