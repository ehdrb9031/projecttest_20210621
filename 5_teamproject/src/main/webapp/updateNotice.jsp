<%@page import="com.hk.dtos.NoticeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>s
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	function noticeList(){
		location.href="NoticeController.do?command=getnotice";
	}
</script>
</head>
<%
	NoticeDto dto = (NoticeDto)request.getAttribute("dto");
%>
<body>
<h1>공지사항 수정</h1>
<form action="NoticeController.do" method="post">
<input type = "hidden" name="command" value="updatenotice"/>
<input type="hidden" name="seq" value="<%=dto.getNo_seq()%>"/>
<table border = "1" class="table table-hover">
	<tr>
		<th>글번호</th>
		<td><%=dto.getNo_seq() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=dto.getId() %></td>
	</tr><tr>
		<th>제목</th>
		<td><input type ="text" name="title" value="<%=dto.getTitle()%>"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" name="content"><%=dto.getContent()%></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value = "수정" class="btn btn-primary"/>
			<button class="btn btn-primary" type="button" onclick="noticeList()">목록</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>