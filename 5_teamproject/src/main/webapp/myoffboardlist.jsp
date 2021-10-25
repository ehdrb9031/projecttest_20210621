<%@page import="com.hk.utils.Util"%>
<%@page import="com.hk.dtos.OffDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp" %> 
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			alert("삭제 되었습니다.");
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
	
	List<OffDto>list=(List<OffDto>)request.getAttribute("list");
%>
<body>
<h1>나의 근무변경 신청 현황</h1>
<form action="OffController.do" method="post" onsubmit="return isChecked()">
<input type="hidden" name="command" value="muldel">
<table class="table table-hover">
   <tr>
      <th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
      <th>번호</th>
      <th>아이디</th>
      <th>제목</th>
      <th>카테고리</th>
      <th>승인</th>
   </tr> 
 <%
		for(int i=0;i<list.size();i++){
			OffDto dto=list.get(i);//list[dto,dto,dto...]--> 순차적으로 하나씩 꺼낸다(dto)
			%>  	
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getOff_seq()%>" /></td>
				<td><%=dto.getOff_seq()%></td>
				<td><%=dto.getId()%></td>
				<td><a href="OffController.do?command=detailoff&seq=<%=dto.getOff_seq()%>"><%=dto.getOff_title()%></td>	
				<td><%=Util.categoryChange((dto.getCategory()))%></td>
				<td><%=Util.offChange(dto.getOff())%></td>
			</tr>
	<%
		}
	%>
	<tr>
		<td colspan="6">
			<input class="btn btn-danger" type="submit" value="삭제" />
			<button class="btn btn-secondary" type="button" onclick="location.href='JoinUserController.do?command=main&id=<%=ldto.getId()%>'">메인</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>