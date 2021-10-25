<%@page import="com.hk.daos.JoinUserDao"%>
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
     
   .admin{
     text-align:center;
      color : red;}  
      
	</style>
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
<h1>Administrator Page</h1>

<h2>근무변경 휴가조회</h2>

<div class="admin">
   <span ><%=ldto.getId() %></span>님 반갑습니다.
</div> 

<form action="OffController.do" method="post" onsubmit="return isChecked()">
<input type="hidden" name="command" value="muldel">
<table class="table table-hover">
   <tr>
      <th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
      <th>번호</th>
      <th>이름</th>
      <th>부서</th>
      <th>직급</th>
      <th>제목</th>
      <th>카테고리</th>
      <th>승인</th>
   </tr> 
 <%
 		JoinUserDao jDao=new JoinUserDao();
		for(int i=0;i<list.size();i++){
			OffDto dto=list.get(i);//list[dto,dto,dto...]--> 순차적으로 하나씩 꺼낸다(dto)
			JoinUserDto jDto=jDao.getListOne(dto.getId());
			%>  	
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getOff_seq()%>" /></td>
				<td><%=dto.getOff_seq()%></td>
				<td><%=jDto.getName()%></td>
				<td><%=Util.dName(jDto)%></td>
				<td><%=Util.rName(jDto)%></td>
				<td> 
				<a href="OffController.do?command=detailoff&seq=<%=dto.getOff_seq()%>&category=<%=dto.getCategory()%>">
					<%=dto.getOff_title()%></td>
				<td><%=Util.categoryChange(dto.getCategory())%></td>
				<td><%=Util.offChange(dto.getOff())%></td>
			</tr>
	<%
		}
	%>
	<tr>
		<td colspan="8">
			<input class="btn btn-danger" type="submit" value="삭제" />		
			<button class="btn btn-secondary" type="button" onclick="location.href='JoinUserController.do?command=main&id=<%=ldto.getId()%>'">메인</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>