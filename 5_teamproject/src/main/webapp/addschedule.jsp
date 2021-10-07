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
</table>
<hr>
<table class="table table-hover">
	<tr>
		<th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
		<th>이름</th>
		<th>부서</th>
		<th>직책</th>
	</tr>
	<tr>
		<%
		for(int i=0;i<list.size();i++){
			String dname="";
			String role="";
			JoinUserDto dto=list.get(i);
			if(dto.getRole()!="HEAD"){
				if(dto.getDname()=="PEDIATRIC"){
					dname="소아과";
				}else if(dto.getDname()=="NEUROLOGY"){
					dname="신경과";
				}else if(dto.getDname()=="PLASTIC"){
					dname="성형회과";
				}else if(dto.getDname()=="EARNOSETHROAT"){
					dname="이비인후과";
				}else if(dto.getDname()=="ORTHOPEDICS"){
					dname="정형외과";
				}else if(dto.getDname()=="INTEGRATED"){
					dname="통합진료과";
				}else if(dto.getDname()=="UROLOGY"){
					dname="비뇨기과";
				}else{
					dname="관리자";
				}
				if(dto.getRole()=="HEAD"){
					dname="간호부장";
				}else if(dto.getRole()=="CHIEF"){
					dname="수간호사";
				}else if(dto.getRole()=="RESPONSIBLE"){
					dname="책임간호사";
				}else{
					dname="평간호사";
				}
			%>
			
				<td><input type="checkbox" name="chk" value="<%=dto.getName() %>" /></td>
				<td><%=dto.getName()%></td>
				<td><%=dname%></td>
				<td><%=role%></td>
			<%
			}
		}
		%>
	</tr>
</table>
</body>
</html> 