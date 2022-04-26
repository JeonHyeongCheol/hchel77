package kr.co.model;

import java.util.ArrayList;
import java.util.Calendar;

public class DateModel {
	public ArrayList<String> getData() { // 년월일을 ArrayList로 반환
		Calendar calendar = Calendar.getInstance();
		
		String y = Integer.toString(calendar.get(Calendar.YEAR));
		String m = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String d = Integer.toString(calendar.get(Calendar.DATE));
		
		ArrayList<String> list = new ArrayList<>();
		list.add(y);
		list.add(m);
		list.add(d);
		
		return list;
	}
}
