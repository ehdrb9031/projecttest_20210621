<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
      <input type="hidden" name="command" value="noticelist">
<%--       <input type="hidden" name="seq" value="${requestScope.dto.no_seq}">       --%>
      <table class="table table-hover">
         <tr>
            <th>작성자</th>
            <td>${ldto.id}</td>
         </tr>
         <tr>
            <th>제목</th>
            <td><input name="title" required="required" type="text"  class="form-control"></td>
         </tr>
         <tr>
            <th>내용</th>
            <td><textarea  name="content" required="required" rows="10" cols="60" class="form-control"></textarea></td>
         </tr>
         <tr>
            <td colspan="2">
               <input type="submit" value="등록하기" class="btn btn-primary">
               <input type="button" value="취소" class="btn btn-primary"
               onclick="location.href='NoticeController.do?command=admin&id=${dto.id}'"/>
            </td>
         </tr>
      </table>
   </form>
</div>
</body>
</html>