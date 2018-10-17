package pack.model;

public class SangPumCalc implements SangPumInter{
	@Override
	public int calcSqngPum(int su, int dan) {
		int sum = su * dan;
		return sum;
	}
}
