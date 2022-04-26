package ex1;

public class Manager extends Regular {
	
	private double incentive;
	
	public Manager(String irum, int nai, int salary) {
		super(irum, nai, salary);
	}
	
	
	@Override
	double pay() {
		if(super.pay() > 2500000) {
			incentive = super.pay() * 0.6;
		} else if(super.pay() <= 2500000 && super.pay() >= 2000000) {
			incentive = super.pay() * 0.5;
		} else {
			incentive = super.pay() * 0.4;
		}
		
		return super.pay() + incentive;
	}
	
	@Override
	void print() {
		super.display();
		System.out.println("수령액은 :" + this.pay());
	}

}
