package pack3;

public class CoinIn {
	private int coin, jandon;

	public void setCoin(int coin) {
		this.coin = coin;
	}
	public int getJandon() {
		return jandon;
	}
	
	public String calc(int cupCount) {
		String msg = "";
		if (coin < 200) {
			msg = "요금 부족(커피 한잔 200)";
		} else if(cupCount * 200 > coin){
			msg = "요금이 부족";
		}else {
			jandon = coin - (200 * cupCount);
			msg = "커피 " + cupCount + "잔과 잔돈 " + 
						jandon + "원";
		}

		return msg;
	}
}
