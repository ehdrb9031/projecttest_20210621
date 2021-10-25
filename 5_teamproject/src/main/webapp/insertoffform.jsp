<%@page import="com.hk.utils.Util"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp" %> 
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
#a{
	font-size: 20pt;
}
#b{
	font-size: 17pt;
}
</style>
</head>
<%
	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
	
%>
<body>
<div id="tablebox">
<h1>근무표 변경 신청</h1>
   <form action="OffController.do" method="post">
      <input type="hidden" name="command" value="insertoff"> 
      <input type="hidden" name="id" value="<%=ldto.getId()%>" >
      <table class="table table-hover">
         <tr>
            <th>작성자</th>
            <td><input type="text" value="<%=ldto.getName()%>" readonly="readonly"></td>
         </tr>
         <tr>
            <th>휴가시작날짜</th>
            <td>
            	<input type="date" name="start" required="required">
			</td>
         </tr>
         <tr>
            <th>휴가마지막날짜</th>
            <td>
            	<input type="date" name="end" required="required">
			</td>
         </tr>
         <tr>
            <th>사유</th>
            <td><input name="title" required="required" type="text"  class="form-control"></td>
         </tr>
         <tr>
            <th>사유 내용</th>
            <td><textarea  name="content" required="required" rows="20" cols="60" class="form-control"></textarea></td>
         </tr>
         <tr>
            <td colspan="2">
               <input type="submit" value="등록" class="btn btn-primary">
               <input type="button" value="취소" class="btn btn-light"
               onclick="location.href='CalController.do?command=myschedule'"/>
            </td>
         </tr>
      </table>
   </form>
</div>
</body>
</html>