<%@page import="com.hk.dtos.NoticeDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@include file="header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	function allSel(bool){
		var chks=document.getElementsByName("chk");
		for(var i = 0; i< chks.length; i++){
			chks[i].checked=bool;
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

</script>
</head>
<%
	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
	
	List<NoticeDto>list=(List<NoticeDto>)request.getAttribute("list");
%>
<body>
<h1>공지사항 목록</h1>
<form action="NoticeController.do" method="post" onsubmit="return isChecked()">
<input type="hidden" name="command" value="muldel">
<table class="table table-hover">
   <tr>
      <th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
      <th>번호</th>
      <th>아이디</th>
      <th>제목</th>
      <th>내용</th>
      <th>조회수</th>
   </tr>
 <%
		for(int i=0;i<list.size();i++){
			NoticeDto dto=list.get(i);//list[dto,dto,dto...]--> 순차적으로 하나씩 꺼낸다(dto)
	%>  	
	<tr>
		<td><input type="checkbox" name="chk" value="<%=dto.getNo_seq()%>" /></td>
		<td><%=dto.getNo_seq()%></td>
		<td><%=dto.getId()%></td>
		<td><%=dto.getTitle()%></td>
	 	<td><a href="NoticeController.do?command=updateform&seq=<%=dto.getNo_seq()%>"><%=dto.getContent()%></td>
		<td><%=dto.getReadcount()%></td>	
	</tr>
	<%
		}
	%>
	<tr>
		<td colspan="6">
			<input class="btn btn-primary" type="submit" value="삭제하기"/>
		</td>
</table>
</form>
리스트 눌렀을 때 수정폼(아이디 비교if문 쓰기 )
</body>
</html>