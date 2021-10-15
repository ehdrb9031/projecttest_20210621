<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
<h1>공지사항</h1>
   <form action="NoticeController.do" method="post">
      <input type="hidden" name="command" value="insertnotice">
<%--       <input type="hidden" name="seq" value="${requestScope.dto.no_seq}">       --%>
      <table class="table table-hover">
         <tr>
            <th>작성자</th>
            <td id = b>${ldto.id}</td>
         </tr>
         <tr>
            <th>제목</th>
            <td id=a><input id=a name="title" required="required" type="text"  class="form-control"></td>
         </tr>
         <tr>
            <th>내용</th>
            <td><textarea  name="content" required="required" rows="20" cols="60" class="form-control"></textarea></td>
         </tr>
         <tr>
            <td colspan="2">
               <input type="submit" value="등록하기" class="btn btn-primary">
               <input type="button" value="취소" class="btn btn-primary"
               onclick="location.href='NoticeController.do?command=noticelist'"/>
            </td>
         </tr>
      </table>
   </form>
</div>
</body>
</html>