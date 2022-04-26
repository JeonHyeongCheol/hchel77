package kr.co.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessagePro implements CommandInter{
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 모델을 수행 한 후 결과를 얻었다고 가정
		request.setAttribute("data", "MessagePro 클래스 수행"); // 속성 값 주기.
		return "view1.jsp"; // jsp 이동.
	}
}
