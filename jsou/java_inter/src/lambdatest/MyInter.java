package lambdatest;

@FunctionalInterface // 추상 메소드 1개만 사용 하도록 강요. 람다를 사용하기 위함.
public interface MyInter {
	int addData(int a, int b);
	//int addData2(int a, int b);

}
