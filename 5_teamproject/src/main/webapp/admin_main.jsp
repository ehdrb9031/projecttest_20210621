<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.JoinUserDao"%>
<%@include file="header.jsp" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
	.box{
		margin : 0 auto;
		border: 1px solid red;
	}
</style>
</head>
<%
	JoinUserDto ldto=(JoinUserDto)session.getAttribute("ldto"); 
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
%>
<body>
<h1>관리자 페이지</h1>
<div>
	<span><%=ldto.getId() %></span>님 반갑습니다.
</div> 
<div class="box">
<nav class="navbar navbar-light bg-light">
  <form class="container-fluid justify-content-start">
    <button class="btn btn-outline-success me-2" type="button" onclick="location.href='JoinUserController.do?command=alluserlist'">간호사전체조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='JoinUserController.do?command=userlist'">재직간호사조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='CalController.do?command=allschedule'">근무표</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='CalController.do?command=perintschedule'">개인근무표작성</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='CalController.do?command=workchangelist'">휴가/근무변경조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='NoticeController.do?command=noticelist'">공지사항목록</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='JoinUserController.do?command=userinfo'">나의정보</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='JoinUserController.do?command=logout'">로그아웃</button>
  </form>
</nav>
</div>
</html> 