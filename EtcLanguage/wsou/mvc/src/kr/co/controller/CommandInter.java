package kr.co.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandInter {
	String showData(HttpServletRequest request, // 보여줄 데이터에 대해 메소드 선언
			HttpServletResponse response) throws Exception; // 추상메소드 선언

	// 모델에서 사용할 메소드 나열
	
}
