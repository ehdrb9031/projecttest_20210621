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
</head>
<%
	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
	
	List<NoticeDto>list=(List<NoticeDto>)request.getAttribute("list");
%>
<body>
<table class="table table-hover">
   <tr>
      <th><input type="checkbox" name = "all" onclick="allSel(this.checked)"/></th>
      <th>번호</th>
      <th>아이디</th>
      <th>제목</th>
      <th>내용</th>
      <th>조회수</th>
   </tr>
<h1>공지사항 목록</h1>
 <%
		for(int i=0;i<list.size();i++){
			NoticeDto dto=list.get(i);//list[dto,dto,dto...]--> 순차적으로 하나씩 꺼낸다(dto)
	%>  	
	<tr>
		<td><input type="checkbox" name="chk" value="<%=dto.getNo_seq()%>" /></td>
		<td><%=dto.getNo_seq()%></td>
		<td><%=dto.getId()%></td>
		<td><%=dto.getTitle()%></td>
	 	<td><%=dto.getContent()%></td>
		<td><%=dto.getReadcount()%></td>	
	</tr>
	<%
		}
	%>
</table>
체크박스,삭제,리스트 눌렀을 때 수정폼(아이디 비교if문 쓰기 )
</body>
</html>