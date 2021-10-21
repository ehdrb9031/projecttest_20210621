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
<style type="text/css">

  h1 {  text-align : center;
          font-size : 35px; 
          margin:40px;  
          font-weight:bold;
       }
    
    h2 {  
    
    text-align: center;       
        background-color : white;  
       font-size : 15px;           
        heght : 8%;
        margin : 20px;
     }
     </style>
<script type="text/javascript"> 
   </script>
</head>     
<%    
	JoinUserDto ldto=(JoinUserDto)session.getAttribute("ldto"); 
	if(ldto==null){
		pageContext.forward("index.jsp");
	}

   JoinUserDto dto=(JoinUserDto)request.getAttribute("dto"); 
%>

<body>
<h1>Administrator Page</h1>

<h2>나의정보</h2>
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
         <button class="btn btn-primary" onclick="location.href='JoinUserController.do?command=updateform'">수정</button>
         <button class="btn btn-danger" onclick="deleteUser('<%=dto.getId()%>')">탈퇴</button>
         <button class="btn btn-info" type="button" onclick="location.href='JoinUserController.do?command=main&id=<%=ldto.getId()%>'">메인</button>
      </td>
   </tr>
</table>
</body>
</html>




