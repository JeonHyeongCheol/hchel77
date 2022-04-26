package controller;

import model.DataDao;

public class SelectServiceImpl implements SelectService { // Xml에 의존적임.
	
	private DataDao dataDao; // 참조형
	
	public SelectServiceImpl(DataDao dataDao) { // 안에 있는 args는 appContext.xml에 DatadaoImpl 주소값을 받아오는 것.
		// 생성자  활용하여 주입(DI)
		this.dataDao = dataDao; // 생성자 생성시 값을 Datadao에게 넘겨줌.
		System.out.println("SelectServiceImpl 수행");
	}
	
	@Override
	public void selectProcess() {
		// DataDao를 생성자에서 생성하고 그 밑에 있는 파생클래스이기 때문에 가져와 사용 가능. 
		// 부모 객체변수 이름으로 자식 메소드를 부름(다형성)
		dataDao.selectData(); 
	}
}
