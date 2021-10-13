<%@page import="com.hk.daos.JoinUserDao"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.utils.Util"%>
<%@include file="header.jsp"%>
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
	JoinUserDao dao=new JoinUserDao();
	List<JoinUserDto> list=dao.getPreUserList();
%>
<body>
<form action="CalController.do" method="post">
<input type="hidden" name="command" value="addnurse" >
	<table class="table table-hover">
		<tr>
			<th><input type="checkbox" name="all" onclick="allSel(this.checked)" /></th>
			<th>이름</th>
			<th>부서</th>
			<th>직책</th>
			<th>근무시간</th>
		</tr>
		
		<%
		for (int i = 0; i < list.size(); i++) {
			JoinUserDto dto = list.get(i);
			if (!dto.getRole().equals("HEAD")) {
		%>
		<tr>
			<td><input type="checkbox" name="chk" value="<%=dto.getId()%>" /></td>
			<td><%=dto.getName()%></td>
			<td><%=Util.dName(dto)%></td>
			<td><%=Util.rName(dto)%></td>
			<td>
				<select class="custom-select d-block w-100" name="wdate" > 
					<option value="day">데이</option> 
					<option value="eve">이브</option> 
					<option value="night">나이트</option> 
				</select> 
			</td>
		</tr>
		<%
			}
		}
		%>
		<tr>
			<td colspan="5">
				<input type="submit" value="선택">
			</td>
		</tr>
	</table>
</form>
</body>
</html> 