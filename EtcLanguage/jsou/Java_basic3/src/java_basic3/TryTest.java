package java_basic3;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class TryTest {
	// 예외처리
	// 프로그램 오류 : 프로그램이 실행 중 어떤 원인에 의해 오작동을 하거나 비정상적으로 종료되는 경우
	// 컴파일 에러 : 컴파일 시에 발생하는 에러
	// 런타임 에러 : 실행 시에 발생하는 에러
	// 논리적 에러 : 실행은 되지만, 의도와 다르게 동작하는 것
	
	// 소스코드 컴파일 할시 오타, 구문, 자료형 체크 후 오류를 알려줌. 성공시 *.class 클래스 파일 생성 후 파일을 실행 시킴
	// 하지만 컴파일을 에러없이 성공적으로 했다고 해도 에러는 있을 수 있음.
	// 자바에서 실행 시 발생 할 수 있는 프로그램 오류 : 에러(error), 예외(exception) 두 가지로 구분함.
	
	// 에러(error) : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
	// 예외(exception) : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류
	
	// 예외 클래스 계층구조
	// Exception(모든 예외 최고 조상) 클래스와 그 자손들 : 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
	// RuntimeExcepiton클래스와 그 자손들 : 프로그래머의 실수로 발생하는 예외
	
	// 예외처리하기 - try - catch문
	// 예외처리 : 프로그램 실행 시 발생할 수 있는 예기치 못한 예외의 발생에 대비한 코드를 작성하는 것.
	// 예외처리(exception handling)의
	//		정의 : 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성하는 것.
	//		목적 : 프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지하는 것.
	
	// try-catch문에서의 흐름
	// try-catch문에서, 예외가 발생한 경우와 발생하지 않았을 때의 흐름이 달라짐.
	// try블럭 내에서 예외가 발생한 경우 : 발생한 예외와 일치하는 catch 블럭이 있는지 확인.
	//							 일치하는 catch블럭을 찾으면 그 catch블럭 내의 문장들을 수행 후 
	//							 전체 try-catch문을 빠져 나간 후 문장을 계속 수행. 일치하는게 없으면 예외처리 안함.
	// try블럭 내에서 예외가 발생하지 않은 경우 : catch블럭을 거치지 않고 전체 try-catch문을 빠져나가 수행을 계속함.
	
	// 예외의 발생과 catch블럭
	// catch블럭 괄호() 내에는 처리하고자 하는 예의와 같은 타입의 참조변수 하나를 선언. 발생한 예의에 해당하는 클래스의 인스턴스가 만들어짐.
	// ex) ArithmeticException이 발생하면 ArithmeticException 인스턴스 생성 
	// true인 catch블럭을 찾으면 그 블럭 수행, 없으면 예외처리 되지 않음.
	
	// printStackTrace()와 getMessage()
	// printStackTrace() : 예외발생 당시의 호출스택(Call stack)에 있었던 메서드의 정보와 예외 메세지를 화면에 출력
	// getMessage() : 발생한 예외클래스의 인스턴스에 저장된 메세지를 얻을 수 있음.
	
	// 멀티 Catch : JDK1.7부터 catch블럭을 '|'기호를 이용해, 하나의 catch블럭으로 합칠 수 있음. 사용시 중복된 코드를 줄임.
	
	// 예외 발생시키기 : 키워드 throw를 사용해 프로그래머가 고의로 예외를 발생 시킬 수 있음.
	// 1. 먼저, 연산자 new를 이용해 발생시키려는 예외 클래스의 객체를 만든 다음
	//	  (Ex]Exception e = new Exception("고의로 발생시켰음");
	// 2. 키워드 throw를 이용해 예외를 발생 (Ex]throw e)
	
	// 메서드의 예외 선언하기
	// Ex) void method() throws Exception... { }
	// 왠만하면 메서드의 예외 처리하게되면 try-catch문으로 예외처리 해야함.
	
	// finally 블럭 : try-catch문과 함께 예외의 발생여부에 상관없이 실행되어야할 코드를 포함시킬 목적으로 사용
	// 순서는 try-catch-finally 순서로 구성
	// 예외가 발생되지 않으면 catch-finally 순으로 구성
	
	//
	
	public void ex( ) {
		try {
			int a[] = {1,2,3};
			System.out.println("배열 요소 : " + a[5]);
		} catch (Exception e) {
			System.out.println("허걱 에러 : " + e);
		}
	}
	
	public void ex2() {
		try {
			FileReader fr2 = new FileReader("C:\\work\\aa.txt"); // 외부 장치와 연결된 것은 오류를 바로바로 잡아 주어야 함. 안그러면 실행 조차 되지 않음.
		} catch (Exception e) {
			System.out.println("허걱 에러 : " + e);
		}
	}
	
	public static void main(String[] args) throws Exception{
		// 예외처리 방법
		// 1. JVM 에게 Throw
		// 2. try ~ catch
		
		try {
		//FileReader fr = new FileReader("C:/work/aa.txt"); 리눅스, 맥에서만 돌아감.
		FileReader fr = new FileReader("C:\\work\\aa.txt"); // FileReader : 파일 불러오기
		
		int k = 10;
		int re = k / 0;
		System.out.println("re: " + re);
		
		TryTest test = new TryTest();
		test.ex();
		test.ex2();
		
		} catch (FileNotFoundException e) { // 파일 오류 
			System.out.println("파일이 없거나 경로 오류");
		} 
		// 하나씩 에러를 잡을 때.
		/* 
		} catch (ArithmeticException e2) { // 연산 오류
			System.out.println("연산오류 : " + e2.getMessage());
		} catch (NullPointerException e3) { // 널 오류
			e3.printStackTrace();
			// 스택에 메서드가 호출된 기록을 남김. 이를 StackTrace라고 하고 에러가 발생한 메소드의 호출 기록을 출력 해줌.
		} catch (ArrayIndexOutOfBoundsException e4) { // 배열 오류
			System.out.println("배열 에러 : " + e4); 
		}
		*/
		
		// 모든 에러 잡을 때.
		catch (Exception e) { // Exception은 모든 에러를 잡아 줌.
			System.out.println("에러 : " + e.getMessage());
		} finally {
			System.out.println("에러와 상관없이 반드시 수행");
		}
		
		System.out.println("프로그램 정상 종료");
	}
}
