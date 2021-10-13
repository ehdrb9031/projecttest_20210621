<%@page import="com.hk.utils.Util"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-3.6.0.js"></script>
<script type="text/javascript">
	function addnurse(){
		window.open("addnurseform.jsp","addnurse","width=570, height=350, resizable = no, scrollbars = no")
	}
</script>
</head>

<%
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String date = request.getParameter("date");
	
%>
<body>
	<h1><%=year%>년<%=month%>월<%=date%>일 근무표 작성</h1>
	<button onclick="addnurse()">추가</button>
</body>
</html>
