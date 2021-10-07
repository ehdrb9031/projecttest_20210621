<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="com.hk.daos.JoinUserDao"%>
<%@include file="header.jsp" %> 
<%
   response.setHeader("Cache-Control", "no-store");
   response.setHeader("Pragma", "no-cache");
   response.setHeader("Expires", "0");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의정보</title>
<script type="text/javascript"> 
   </script>
</head>     
<%    
   JoinUserDto dto=(JoinUserDto)request.getAttribute("dto"); 
%>

<body>
<h1>나의정보</h1>
<table border="1">
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
      <td><%=dto.getAddress()%></td>
   </tr>
   <tr>
      <th>전화번호</th>
      <td><%=dto.getPhone()%></td>
   </tr>
   <tr>
      <th>이메일</th>
      <td><%=dto.getEmail()%></td>
   </tr>    
   <tr> 
      <th>직급</th>
   <td><%=dto.getRole()%></td>   
   </tr>
   <tr>  
      <th>부서</th>  
      <td><%=dto.getDname()%></td>
   </tr>
   
   <tr>
      <td colspan="2">
         <button onclick="updateForm('<%=dto.getId()%>')">수정</button>
         <button onclick="deleteUser('<%=dto.getId()%>')">탈퇴</button>
         <button onclick="location.href='admin_main.jsp'">메인</button> 
      </td>
   </tr>
</table>
</body>
</html>




