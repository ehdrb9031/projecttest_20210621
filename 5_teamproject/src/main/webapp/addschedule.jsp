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
<script type="text/javascript" src="jquery-3.6.0.js"></script>
<script type="text/javascript">
	function addUser(){
		var chks=document.getElementsByName("chk");
		var day=document.getElementsByClassName("day")[0].children;
		for (var i = 0; i < chks.length; i++) {
			if(chks[i].checked==true){
// 				alert(chks[i].parentElement.nextElementSibling.innerHTML);
			}		
		}	
	}
	$(function(){
		$("#selectCal").click(function(){
			var chks=document.getElementsByName("chk");
			for (var i = 0; i < chks.length; i++) {		
				var name=chks[i].parentElement.nextElementSibling.innerHTML;
				$.ajax({//key:value	서버쪽에 갔다가 응답을 해서 왔는데 페이지가 새로 로딩되지 않고 작업을 해준다. -> 비동기식
					url:"CalController.do", //요청 URL
					method:"post", //전송방식
					data:{"command":"contentAjax","name":name}, //server로 보낼 값
	//		 		dataType:"text",  //server로부터 응답받는 값(json, xml, html, text...)의 타입
					dataType:"text",
					async:false, //javascript에서 ajax메서드의 실행을 비동기로 할지말지 여부
					success:function(obj){//통신성공하면 기능 실행(obj변수는 전달된 데이터를 받는다)
						$(".daylist").val(obj);
					},
					error:function(){
						alert("서버통신실패");
					}
				});
			}
		}
	})

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
		<th>
			<button class="day">데이</button>
			<div class="daylist"></div>
		</th>
		<th><button>나이트</button></th>
		<th><button>이브</button></th> 
	</tr>
</table>
<hr>
<table class="table table-hover" id="userTable">
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
         <button class="btn btn-primary" id="selectCal" onclick="addUser()">선택</button>
      </td>
   </tr>
</table>
</body>
</html> 