<%@page import="com.hk.utils.Util"%>
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
		<th><button>데이</button></th>
		<th><button>나이트</button></th>
		<th><button>이브</button></th> 
	</tr>
</table>
<hr>
<table class="table table-hover">
	<tr>
		<th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
		<th>이름</th>
		<th>부서</th>
		<th>직책</th>
	</tr>
	
		<%
		for(int i=0;i<list.size();i++){
			JoinUserDto dto=list.get(i);
			if(dto.getRole()!="HEAD"){		
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getName() %>" /></td>
				<td><%=dto.getName()%></td>
				<td><%=Util.dName(dto)%></td> 
				<td><%=Util.rName(dto)%></td>
			</tr>
			<%
			}
		}
		%>
	
</table>
</body>
</html> 