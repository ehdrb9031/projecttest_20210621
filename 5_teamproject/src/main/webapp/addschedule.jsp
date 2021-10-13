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
	
	List<JoinUserDto>list=(List<JoinUserDto>)request.getAttribute("list");
%>
<body>
	<h1><%=year%>년<%=month%>월<%=date%>일 근무표 작성</h1>
	<form action="CalController.do" method="post">
	<input type="hidden" name="command" value="addschedule" >
	<input type="hidden" name="year" value="<%=year%>"/>
	<input type="hidden" name="month" value="<%=month%>"/>
	<input type="hidden" name="date" value="<%=date%>"/>
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
