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
	List<JoinUserDto>list=(List<JoinUserDto>)request.getAttribute("list");
// 	JoinUserDao dao = new JoinUserDao();
//  	List<JoinUserDto> list = dao.getUserList();
 	
%>
<body>
<div class="box">
<nav class="navbar navbar-light bg-light">
  <form class="container-fluid justify-content-start">
    <button class="btn btn-outline-success me-2" type="button" onclick="href='JoinUserController.do?command=alluserlist'">간호사전체조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="href='JoinUserController.do?command=userlist'">재직간호사조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="href='JoinUserController.do?command=insertschedule'">근무표작성</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="href='JoinUserController.do?command=workchangelist'">휴가/근무변경조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="href='JoinUserController.do?command=addnotice'">공지사항등록</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="location.href='JoinUserController.do?command=userinfo'">나의정보</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="href='JoinUserController.do?command=logout'">로그아웃</button>
  </form>
</nav>
</div>
<div id="tablebox">
<h1>관리자 페이지</h1>
<h2>간호사 전체조회</h2>
<table class="table table-hover">
   <tr>
      <th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
      <th>번호</th>
      <th>이름</th>
      <th>전화번호</th>
      <th>EMAIL</th>
      <th>주소</th>
      <th>부서</th>
      <th>직급</th>
      <th>재직</th>
   </tr>
   <%
		for(int i=0;i<list.size();i++){
			JoinUserDto dto=list.get(i);//list[dto,dto,dto...]--> 순차적으로 하나씩 꺼낸다(dto)
	%>  	
	<tr>
		<td><input type="checkbox" name="chk" value="<%=dto.getSeq() %>" /></td>
		<td><%=dto.getSeq()%></td>
		<td><%=dto.getName()%></td>
		<td><%=dto.getPhone()%></td>
	 	<td><%=dto.getEmail()%></td>
		<td><%=dto.getAddress()%></td>
		<td><%=dto.getDname()%></td>
		<td><%=dto.getRole().equals("USER")?"간호사":"관리자"%></td>
		<td><%=dto.getEnabled().equals("Y")?"재직":"퇴직"%></td>  	
	</tr>
	<%
		}
	%>
			
			
   
</table>
</div>
</html> 