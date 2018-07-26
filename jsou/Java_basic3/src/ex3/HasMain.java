package ex3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class HasMain {
	ArrayList<Hsa> list;
	DecimalFormat format = new DecimalFormat("#,##0"); // 1000 이넘으면 콤마 찍기
	
	public HasMain() {
		list = new ArrayList<Hsa>();
	}
	
	public void inputData(String[] args) {
		//HaksaengDto dto; 요렇게 써도되고
		
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
			StringTokenizer tok = new StringTokenizer(args[i],",");
			int sab = Integer.parseInt(tok.nextToken());
			String irum = tok.nextToken();
			int basic = Integer.parseInt(tok.nextToken());
			int say = Integer.parseInt(tok.nextToken());
			
			Hsa hsa = new Hsa(); 
			hsa.setSab(sab); //hsa.setSab(Integer.parseInt(tok.nextToken())); 둘다 사용가능.
			hsa.setIrum(irum); //hsa.setIrum(tok.nextToken()); 둘다 사용가능.
			hsa.setBasic(basic); //hsa.setBasic(Integer.parseInt(tok.nextToken())); 둘다 사용가능.
			hsa.setSay(say); //hsa.setSay(Integer.parseInt(tok.nextToken())); 둘다 사용가능.
			list.add(hsa);
		}

	}
	
	public void printData() {
		System.out.println("사번\t이름\t기본급\t근무년수\t근속수당\t공제액\t수령액");
		for(Hsa hsa:list) {
//			for(i=0;i<list.size();i++) { // for는 이렇게 사용 가능
//				Has hsa = list.get(i); // list를 Has타입으로 바꿈.
//			}
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(calendar.YEAR)-hsa.getSay(); 
			int ky = 0; // 근속수당액
			int kysum = 0; // 급여총액
			double ks = 0; // 공제액
			
		
			
			if(year >= 9) {
				ky = 100000;
				kysum = hsa.getBasic()+ky;
			} else if(year >= 4) {
				ky = 450000;
				kysum = hsa.getBasic()+ky;
			} else {
				ky = 150000;
				kysum = hsa.getBasic()+ky;
			}
			
			if(kysum >= 3000000) {
				ks = kysum * 0.05;
			} else if(kysum >= 2000000) {
				ks = kysum * 0.03;
			} else {
				ks = kysum * 0.015;
			}
			
			double sum = kysum - ks; // 총액
			
			System.out.println(hsa.getSab() + "\t" + hsa.getIrum()+ "\t" + hsa.getBasic() + "\t" + year + "\t" + ky + "\t" + (int)ks + "\t" + format.format((int)sum));
		}
		System.out.println("처리건수 : " + list.size() + "건");
	}
	
	
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("입력자료 없음. 잘가~~~");
			System.exit(0);
		} else {
			HasMain ma = new HasMain();
			ma.inputData(args);
			ma.printData();
			
		}
	}
}
