package pack.gogek;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pack.bank.Bank;
import pack.bank.HanaBank;
import pack.bank.ShinhanBank;

@Service
@ComponentScan("pack.bank") // 클래스 자체에서 스캔 시킬 수 있음.
@Scope("prototype")
public class Gogek {
	private Bank bank;
	
	@Autowired(required = true) // 반드시 있어야 됨.
	private ShinhanBank shinhanBank;
	
	@Resource(name = "hana")
	private HanaBank hanaBank;
	
	public void selBank(String sel) {
		if(sel.equals("sin")) {
			bank = shinhanBank;
		} else if(sel.equals("ha")) {
			bank = hanaBank;
		}
	}
	
	public void playInputMoeny(int money) {
		bank.inputMoney(money);
	}
	
	public void playOutputMoney(int money) {
		bank.outputMoney(money);
	}
	
	private String msg;
	@PostConstruct
	public void init() { // 초기화.
		msg = "계좌 잔고 : ";
	}
	
	@PreDestroy
	public void end() { // 마무리.
		System.out.println("프로그램 종료 시 마무리 처리");
		if(shinhanBank != null) shinhanBank = null; // 값이 있을 시 Null로 변경.
		if(hanaBank != null) hanaBank = null;
	}
	
	public void showMoney( ) {
		System.out.println(msg + bank.getMoney());
	}
}
