package aop1;

public class WriteInterImpl implements WriteInter{
	public WriteInterImpl() {
		System.out.println("WriteInterImpl 생성자");
	}
	
	@Override
	public void write() {
		try {
			Thread.sleep(3000);
			System.out.println("write() 수행 : 3초");
		} catch (Exception e) {
			System.out.println("write Err : " + e);
		}
		
	}
}
