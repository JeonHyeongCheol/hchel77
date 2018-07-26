package ex1;

public class Temporary extends Employee {
	private int ilsu = 0;
	private int ildang = 0;
	
	public Temporary(String irum, int nai, int ilsu, int ildang) {
		super(irum, nai);
		this.ildang = ildang;
		this.ilsu = ilsu;
	}
	
	@Override
	double pay() {
		return ilsu * ildang;
	}
	
	@Override
	void print() {
		super.display();
		System.out.println("월급은 : " + this.pay());
	}
	
}
