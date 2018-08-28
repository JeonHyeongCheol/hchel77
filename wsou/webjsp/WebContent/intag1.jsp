<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
홀수 출력<br>
<%
for(int i=0; i <= 10; i++) {
	if(i % 2 == 1) out.print(i + " ");
}
%>