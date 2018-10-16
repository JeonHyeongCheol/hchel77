package kr.mvc.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.mvc.controller.UserForm;

public class UserDaoModel { // Maria DB와 통신하는 클래스 - 중간에 Mybatis가 있는 것!
	// 모델 영역 - DB와 연동 등에 Process
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public UserDto findUser(String userid) {
		UserDto dto = null;
		
		SqlSession session = factory.openSession();
		try {
			dto = session.selectOne("findUser", userid);
		} catch (Exception e) {
			System.out.println("findUser err : " + e);
		} finally {
			if(session != null) session.close();
		}
		
		return dto;
	}
	
	public ArrayList<UserDto> getUserAll() {
		List<UserDto> list = null;
		
		SqlSession session = factory.openSession();
		try {
			list = session.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("getUserAll err : " + e);
		} finally {
			if(session != null) session.close();
		}
		return (ArrayList<UserDto>)list; // 반환되는 타입형을 맞추기 위해 캐스팅.
	}
	/*
	public static void UserInsert(UserForm form) {
		SqlSession session = factory.openSession();
		try {
			list = session.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("getUserAll err : " + e);
		} finally {
			if(session != null) session.close();
		}
		return (ArrayList<UserForm>)list; // 반환되는 타입형을 맞추기 위해 캐스팅.
	}
	*/
}
