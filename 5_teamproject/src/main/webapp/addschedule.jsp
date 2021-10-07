<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String year=request.getParameter("year");
	String month=request.getParameter("month");
	String date=request.getParameter("date");
	
	List<JoinUserDto>list=(List<JoinUserDto>)request.getAttribute("list");
%>
<body>
<h1><%=year%>년<%=month%>월<%=date%>일 근무표 작성</h1>
<table class="table table-hover">
	<tr>
		<th>데이</th>
		<th>나이트</th>
		<th>이브</th>
	</tr>
	<tr>
		<%
		for(int i=0;i<list.size();i++){
			JoinUserDto dto=list.get(i);
			%>
			<td><%=dto.getName()%></td>
			<%
		}
		%>
	</tr>
</table>
</body>
</html> 