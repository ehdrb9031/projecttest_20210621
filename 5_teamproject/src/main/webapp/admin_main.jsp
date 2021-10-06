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
<body>
<div class="box">
<nav class="navbar navbar-light bg-light">
  <form class="container-fluid justify-content-start">
    <button class="btn btn-outline-success me-2" type="button" onclick="">간호사전체조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="">재직간호사조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="">근무표작성</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="">휴가/근무변경조회</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="">공지사항등록</button>
	<button class="btn btn-outline-success me-2" type="button" onclick="">로그아웃</button>
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
</table>
</div>
</html> 