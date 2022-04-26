<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<b style="color: red"> 
<% String msg = request.getParameter("msg"); %>
<%= "메세지는 : " + msg %>
<% // Parameter를 받아와서 출력도 해줄 수 있음. %>
</b>