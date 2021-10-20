<%@page import="com.hk.utils.Util"%>
<%@page import="com.hk.dtos.OffDto"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴가 상세조회</title>
</head>
<%
	OffDto oDto=(OffDto)request.getAttribute("oDto");
	JoinUserDto jDto=(JoinUserDto)request.getAttribute("jDto");

	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
	
%>
<body>
<h1>휴가 상세조회</h1>
<table border="1" class="table table-hover">
	<tr>
		<th>글번호</th>
		<td><%=oDto.getOff_seq() %></td>
	</tr>
	<tr>
		<th>아이디</th>
		<td><%=oDto.getId() %></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><%=jDto.getName()%></td>
	</tr>
	<tr>
		<th>부서</th>
		<td><%=Util.dName(jDto)%></td>
	</tr>
	<tr>
		<th>직급</th>
		<td><%=Util.rName(jDto)%></td>
	</tr>
	<tr>
		<th>휴가시작일자</th>
		<td><%=oDto.getWdate()%></td>
	</tr>
	<tr>
		<th>휴가마지막일자</th>
		<td><%=oDto.getOdate()%></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><%=oDto.getOff_title()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=oDto.getOff_content()%></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
		<%
				if(ldto.getRole().equals("HEAD")){
				%>					
					<button class="btn btn-outline-success me-2" type="button" onclick="location.href='OffController.do?command=offva&seq=<%=oDto.getOff_seq() %>&id=<%=oDto.getId()%>&odate=<%=oDto.getOdate()%>&wdate=<%=oDto.getWdate()%>'">승인</button>
					<button class="btn btn-outline-success me-2" type="button" onclick="location.href='OffController.do?command=offNo&seq=<%=oDto.getOff_seq()%>'">반려</button>
				<%
				}else{
				%>
					<button onclick="location.href='OffController.do?command=myoffboardlist'">목록</button>
				<%
				}
			%>
		</td>
	</tr>
</table>
</body>
</html>