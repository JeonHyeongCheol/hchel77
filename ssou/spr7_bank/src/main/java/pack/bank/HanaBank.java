package pack.bank;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("hana")
@Scope("prototype")
public class HanaBank implements Bank{
	private int money = 1000;
	
	@Override
	public void inputMoney(int money) {
		if(money > 0) {			
			this.money = money;
		}
	}
	
	@Override
	public void outputMoney(int money) {
		if(getMoney() >= money) { // 잔고가 money보다 크거나 같아야 뺄 수 있음.
			this.money = money;			
		}
	}
	
	@Override
	public int getMoney() {
		return money;
	}
	
}
