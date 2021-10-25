<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="com.hk.daos.JoinUserDao"%>
<%@include file="header.jsp"%>
<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>

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
<title>나의정보</title>
<script type="text/javascript">
	
</script>
</head>
<%
	JoinUserDto dto = (JoinUserDto) request.getAttribute("dto");
%>

<body>
	<h1>정보수정</h1>
	<form action="JoinUserController.do" method="post" >
	<input type="hidden" name="command" value="after_updateform" >
	<input type="hidden" name="id" value="<%=dto.getId()%>" >
	<table border="1" class="table table-hover"> 
		<tr>
			<th>아이디</th>
			<td><%=dto.getId()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getName()%></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="address" placeholder="서울특별시 강남구"
				required></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="phone" placeholder="01012345678" required></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="email" name="email"
				placeholder="you@example.com" required></td>
		</tr>
		<tr>
			<th>직급</th>
			<td>
				<select class="custom-select d-block w-100" name="role">
					<option value="HEAD">간호부장</option>
					<option value="CHIEF">수간호사</option>
					<option value="RESPONSIBLE">책임간호사</option>
					<option value="NURSE">평간호사</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>
				<select class="custom-select d-block w-100" name="dname"> 
					<option value="PEDIATRIC">소아과</option> 
					<option value="NEUROLOGY">신경과</option> 
					<option value="PLASTIC">성형회과</option> 
					<option value="EARNOSETHROAT">이비인후과</option> 
					<option value="ORTHOPEDICS">정형외과</option> 
					<option value="INTEGRATED">통합진료과</option> 
					<option value="UROLOGY">비뇨기과</option> 
				</select> 
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-success">수정</button>
				<button class="btn btn-danger"
					onclick="deleteUser('<%=dto.getId()%>')">탈퇴</button>
				<button class="btn btn-secondary"
					onclick="location.href='JoinUserController.do?command=main&id=<%=dto.getId()%>'">메인</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>




