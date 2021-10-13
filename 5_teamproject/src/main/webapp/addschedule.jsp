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
	function allSel(bool){
		var chks=document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;
		}
	}
	function addnurse(){
		var chks=document.getElementsByName("chk");
		var td=document.getElementById("addtd");
		for (var i = 0; i < chks.length; i++) {
			if(chks[i].checked){
// 				alert(chks[i].parentElement.nextElementSibling.innerHTML);
				td=chks[i].parentElement.nextElementSibling.innerHTML;
			}
		}
	}
	
	
</script>
</head>

<%
String year = request.getParameter("year");
String month = request.getParameter("month");
String date = request.getParameter("date");

List<JoinUserDto> list = (List<JoinUserDto>) request.getAttribute("list");
%>
<body>
	<h1><%=year%>년<%=month%>월<%=date%>일 근무표 작성</h1>
	<table class="table table-hover">
	<h4>근무자 추가현황</h4>
		<tr>
			<th><input type="checkbox" name="all"
				onclick="allSel(this.checked)" /></th>
			<th>이름</th>
			<th>부서</th>
			<th>직책</th>
			<th>근무시간</th>
		</tr>
		<tr>
			<td id="addtd"></td>
		</tr>
	</table>
	<hr>
	<table class="table table-hover">
	<h4>전체 근무자</h4>
		<tr>
			<th><input type="checkbox" name="all" onclick="allSel(this.checked)" /></th>
			<th>이름</th>
			<th>부서</th>
			<th>직책</th>
		</tr>

		<%
		for (int i = 0; i < list.size(); i++) {
			JoinUserDto dto = list.get(i);
			if (dto.getRole() != "HEAD") {
		%>
		<tr>
			<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>" /></td>
			<td><%=dto.getName()%></td>
			<td><%=Util.dName(dto)%></td>
			<td><%=Util.rName(dto)%></td>
		</tr>
		<%
		}
		}
		%>
		<tr>
			<td colspan="4">
				<button onclick="addnurse()">추가</button>
			</td>
		</tr>
	</table>
</body>
</html>
