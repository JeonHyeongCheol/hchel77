package ex1;

public class Regular extends Employee {
	private double salary;
	
	 public Regular(String irum, int nai, double salary) {
		super(irum, nai);
		this.salary = salary;
	}
	
	@Override
	double pay() {
		return salary;
	}
	
	@Override
	void print() {
		super.display();
		System.out.println("월급은 : " + this.pay()); 
	}
	
}
