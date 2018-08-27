package servlet_ex1;

public class Stus {
	private int pnum;
	private String pname;
	private int pkor;
	private int peng;
	
	public Stus(int pnum, String pname, int pkor, int peng) {
		this.pnum = pnum;
		this.pname = pname;
		this.pkor = pkor;
		this.peng = peng;
	}
	
	public int getPnum() {
		return pnum;
	}
	
	public String getPname() {
		return pname;
	}
	
	public int getPkor() {
		return pkor;
	}
	
	public int getPeng() {
		return peng;
	}
	
	public int getSum() {
		return pkor + peng;
	}
	
}
