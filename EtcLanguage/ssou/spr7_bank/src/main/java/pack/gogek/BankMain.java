package pack.gogek;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("bankinit.xml");
		
		Gogek daniel = (Gogek)context.getBean("gogek");
		daniel.selBank("sin");
		daniel.playInputMoeny(2000);
		daniel.playOutputMoney(1000);
		System.out.print("다니엘-");
		daniel.showMoney();
		
		System.out.println();
		Gogek john = (Gogek)context.getBean("gogek");
		john.selBank("sin");
		john.playInputMoeny(2000);
		john.playOutputMoney(1000);
		System.out.print("존-");
		john.showMoney();
		
		context.close();
	}
}
