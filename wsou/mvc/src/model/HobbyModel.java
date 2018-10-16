package model;

import java.util.ArrayList;

public class HobbyModel {
	public static HobbyModel getinstance() { // Singleton 생성. Spring에서는 기본적으로 Singleton 사용.
		return new HobbyModel();
	}
	
	public ArrayList<String> getHobby(String hobby) { // 반환값은 ArrayList
		ArrayList<String> list = new ArrayList<>();
		if(hobby.equals("m")) {
			list.add("설악산");
			list.add("오대산");
		} else if(hobby.equals("t")) {
			list.add("동해안");
			list.add("서해안");
			list.add("남해안");
		} else if(hobby.equals("s")) {
			list.add("수영장");
		} else if(hobby.equals("r")) {
			list.add("마라톤");
			list.add("동네 한바퀴");
		}
		
		return list;
	}
}
