<%@page import="com.hk.utils.Util"%>
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
		border: 1px solid red;}
		
	 h1 {   
     text-align : center;
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
    
  .admin {
  
     text-align: center;       
     background-color : white;  
     font-size : 15px;           
     heght : 8%;
     margin : 20px;
   }
   
     .user{  text-align: center;       
          background-color : white;  
          font-size : 15px;           
          margin : 10px;
          color :  blue;
   }         	
	}
</style>
<script type="text/javascript">
	function allSel(bool){
		var chks=document.getElementsByName("chk");
		for(var i = 0; i< chks.length; i++){
			chks[i].checked=bool;
		}
	}
	function isChecked(){
		var count=0;
		var chks=document.getElementsByName("chk");
		for(var i =0; i < chks.length; i++){
		   if(chks[i].checked){
		      count++;
		   }
		}
		if(count == 0){
		   alert("최소 하나 선택하세요");
		   return false;
		}else{
		   return true;
		}
	}
	onload = function(){
		var chks = document.getElementsByName("chk");
		
		for(var i = 0; i < chks.length; i++){
			chks[i].onclick=function(){
				var count = 0;
				for(var i =0; i < chks.length; i++){
					if(chks[i].checked){
						count++;
					}
				}
				if(count == chks.length){
					a = document.getElementsByName("all")[0];
		 			a.checked = true;
				}else{		
					b = document.getElementsByName("all")[0];
		 			b.checked = false;
				}	
			}
		}
	}
</script>
</head>
<%
	List<JoinUserDto>list=(List<JoinUserDto>)request.getAttribute("list");

	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
 	
%>
<body>
<div id="tablebox">
<h1>Administrator Page</h1>
<h2>간호사 전체조회</h2>
<form action="JoinUserController.do" method="post" onsubmit="return isChecked()">
<input type="hidden" name="command" value="muldel" >
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
		<td><%=Util.dName(dto)%></td>
		<td><%=Util.rName(dto)%></td> 
		<td><%=dto.getEnabled().equals("Y")?"재직":"퇴직"%></td>  	
	</tr>
	<%
		}
	%>
	<tr>
      <td colspan="9">
         <input class="btn btn-primary" type="submit" value="삭제"/>
         <button class="btn btn-info" type="button" onclick="location.href='JoinUserController.do?command=main&id=<%=ldto.getId()%>'">메인</button>
      </td>
   </tr>
</table>
</form>
</div>
</body>
</html> 