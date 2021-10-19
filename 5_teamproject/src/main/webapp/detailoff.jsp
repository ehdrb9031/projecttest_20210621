<%@page import="com.hk.dtos.OffDto"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세조회</title>
</head>
<%
	OffDto dto=(OffDto)request.getAttribute("dto");

	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
	
%>
<body>
<h1>공지사항 상세조회</h1>
<table border="1" class="table table-hover">
	<tr>
		<th>글번호</th>
		<td><%=dto.getOff_seq() %></td>
	</tr>
	<tr>
		<th>아이디</th>
		<td><%=dto.getId() %></td>
	</tr>
	<tr>
		<th>이름</th>
		<td></td>
	</tr>
	<tr>
		<th>부서</th>
		<td></td>
	</tr>
	<tr>
		<th>직급</th>
		<td></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><%=dto.getOff_title()%></td>
	</tr>
	<tr>
		<th>근무일자</th>
		<td><%=dto.getWdate()%></td>
	</tr>
	<tr>
		<th>변경일자</th>
		<td><%=dto.getOdate()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getOff_content()%></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button class="btn btn-outline-success me-2" type="button" onclick="location.href='OffController.do?command=offYes">승인</button>
			<button class="btn btn-outline-success me-2" type="button" onclick="location.href='OffController.do?command=offNo'">반려</button>
		</td>
	</tr>
</table>
</body>
</html>