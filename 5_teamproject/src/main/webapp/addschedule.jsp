<%@page import="com.hk.utils.Util"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function daySchedule(){
		document.getElementById("userTable").show();
	}
	function addUser(){
		var chks=document.getElementsByName("chk");
		var day=document.getElementById("dayButton");
		var name;
		for (var i = 0; i < chks.length; i++) {
			if(chks[i].checked==true){
// 				alert(chks[i].parentElement.nextElementSibling.innerHTML);
				day.innerHTML=chks[i].parentElement.nextElementSibling.innerHTML;
				
			}		
		}	

// 		document.getElementsByTagName("tr")[2].children().first();
	}
</script>
</head>

<%
	String year=request.getParameter("year");
	String month=request.getParameter("month");
	String date=request.getParameter("date");
	
	List<JoinUserDto>list=(List<JoinUserDto>)request.getAttribute("list");
	
%>
<body>
<h1><%=year%>년<%=month%>월<%=date%>일 근무표 작성</h1>
<table class="table table-hover">
	<tr>
		<th><button onclick="daySchedule()">데이</button><div id="dayButton"></div></th>
		<th><button>나이트</button></th>
		<th><button>이브</button></th> 
	</tr>
</table>
<hr>
<table class="table table-hover" id="userTable" >
	<tr>
		<th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
		<th>이름</th>
		<th>부서</th>
		<th>직책</th>
	</tr>
	
		<%
		for(int i=0;i<list.size();i++){
			JoinUserDto dto=list.get(i);
			if(dto.getRole()!="HEAD"){		
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getName() %>" /></td>
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
         <button class="btn btn-primary" onclick="addUser()">선택</button>
      </td>
   </tr>
</table>
</body>
</html> 