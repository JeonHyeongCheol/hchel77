package kr.co.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.model.DateModel;

public class DatePro implements CommandInter{
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 모델을 수행
		DateModel dateModel = new DateModel(); // DateModel 객체 선언
		ArrayList<String> list = dateModel.getData(); // 년월일 메소드를 list가 가짐.
		request.setAttribute("data", list); // 보내줄 값 설정.
		
		return "view2.jsp"; // jsp로 이동
	}

}
