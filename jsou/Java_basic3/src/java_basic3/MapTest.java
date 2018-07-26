package java_basic3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		// Map 인터페이스 : 키(key)와 값(value)을 하나의 쌍으로 묶어서 저장하는 컬렉션 클래스를 구현하는데 사용.
		//				 키는 중복 될 수 없지만 값은 중복을 허용. 
		//				 기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게 됨.
		// Map 인터페이스를 구현한 클래스 : HashTable, HashMap, LinkedHashMap, SortedMap, TreeMap 등
		// Map.Entry 인터페이스 : Map 인터페이스의 내부 인터페이스, Map에 저장되는 Key-value쌍을 다루기 위해 내부적으로 Entry인터페이스를 정의.
		//					    보다 객체지향적으로 설계하도록 유도하기 위한 것.
		
		HashMap<String, String> list = new HashMap<>(); // <key, value> 키와 값을 어떤 타입으로 설정할 것인지.
		// HashMap : Map으로 구성했기 때문에 키와 값을 묶어서 하나의 데이터(entry)로 저장한다는 특징이 있음.
		//		 	 Hashing을 사용하기 때문에 많은 양의 데이터를 검색하는데 있어서 뛰어난 성능을 보임.
		// 			 HashMap는 키와 값을 각각 Object타입으로 저장. 즉(Object, Object)의 형태로 저장하기 때문에 어떠한 객체도 저장 가능.
		//  		 보통 String을 대문자 또는 소문자로 통일해서 사용.
		// 키(Key) : 컬렉션 내의 키(key) 중에서 유일해야 함.
		// 값(value) : 키(key)와 달리 데이터의 중복을 허용.
		
		// 해싱과 해싱함수
		// 해싱 : 해시함수(Hash Function)을 이용해서 데이터를 해시테이블에 저장하고 검색하는 기법
		// 해시함수 : 데이터가 저장되어 있는 곳을 알려 주며 다량의 데이터 중에서도 원하는 데이터를 빠르게 찾을 수 있음.
		// 해싱으로 구현한 컬렉션 클래스 : HashSet, HashMap, HashTable 등
		
		// TreeMap : 이진검색트리의 형태로 키와 값의 쌍으로 이루어진 데이터를 저장. 
		
		list.put("0","lee");
		list.clear();
		
		list.put("0","lee");
		list.put("1","lee1");
		list.put("2","lee2");
		list.put("3","lee3");
		list.put("4","lee4");
		list.put("5","lee5");
		list.put("2","park"); // 같은 키 값은 덮어 쓰게 됨. 2번은 lee1에서 park으로 바뀜.
		System.out.println(list.size());
		System.out.println(list.containsKey("1")); // containsKey : 키값에 값이 있는지 없는지 확인.
		System.out.println(list.containsValue("lee2")); // containsValue : 입력한 값과 일치하는 값이 있는 확인.
		list.remove("0");
		
		System.out.println("-----------");
		
		display(list);
		
	}
	
	public static void display(Map map) { // hashmap는 map에 서브클래스
		Set set = map.keySet(); // set 타입으로 넘김.
		Iterator iter = set.iterator(); // set을 Iterator로 넘김.
		while(iter.hasNext()) { // hash에 있는 것.
			String key = (String)iter.next(); // 키값이 int이기 때문에 String으로 캐스팅하여 함.
			System.out.println(key + " " + map.get(key)); 
		}
	}
}
