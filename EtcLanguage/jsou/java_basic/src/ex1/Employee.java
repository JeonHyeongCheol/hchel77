package ex1;

abstract public class Employee {
	
	private String irum = "";
	private int nai = 0;
	
	public Employee(String irum, int nai) {
		this.irum = irum;
		this.nai = nai;
	}
	
	abstract void print();
	abstract double pay();

	public void display() {
		System.out.println("이름은 : " + irum + " " + "나이는 : " + nai);
	}
}
