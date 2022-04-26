package kr.mvc.model;

import java.util.ArrayList;

import kr.mvc.controller.UserForm;

public class UserManager { // Servlet과 DB를 연결 해주는 Manager.
	private static UserManager manager = new UserManager();
	public static UserManager getinstance() { // 여기에는 UserdaoModel 클래스도 포함되어 있음.
		return manager;
	}
	
	private UserDaoModel getUserDaoModel() {
		return new UserDaoModel(); // UserDaoModel에 있는 DB값을 확인 후 return해줌.
	}
	
	public boolean login(String user_id, String user_password) { // id, pwd 확인.
		UserDto dto = getUserDaoModel().findUser(user_id);
		if(dto == null) return false;
		
		if(dto.getPassword().equals(user_password)) { // DB에서 읽어온 pwd와 클라이언트로 부터 가져온 pwd가 맞는지 확인.
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<UserDto> getUserAll() {
		return getUserDaoModel().getUserAll();
	}
	
	public UserDto findUser(String userid) { 
		return getUserDaoModel().findUser(userid);
	}
	
	public int insertData(UserForm userForm) {
		return getUserDaoModel().insertData(userForm);
	}
	
	public int updateData(UserForm userForm) {
		return getUserDaoModel().updateData(userForm);
	}
	
	public int deleteData(String userid) {
		return getUserDaoModel().deleteData(userid);
	}
}
