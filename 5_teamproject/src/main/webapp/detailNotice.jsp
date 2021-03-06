<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="com.hk.dtos.NoticeDto"%>
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
<title>공지사항 상세조회</title>
</head>
<%
	NoticeDto dto=(NoticeDto)request.getAttribute("dto");
	session.getAttribute("readcount");

	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
	
%>
<body>
<h1>공지사항 상세조회</h1>
<table border="1" class="table table-hover">
	<tr>
		<th>글번호</th>
		<td><%=dto.getNo_seq() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=dto.getId() %></td>
	</tr><tr>
		<th>제목</th>
		<td><%=dto.getTitle()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent()%></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<%
				if(dto.getId().equals(ldto.getId())){
				%>					
					<button onclick="location.href='NoticeController.do?command=updateform&seq=<%=dto.getNo_seq()%>'" class="btn btn-success">수정</button>
					<button class="btn btn-danger" onclick="delBoard()" >삭제</button>
					<button class="btn btn-secondary" onclick="location.href='NoticeController.do?command=noticelist'">목록</button>
				<%
				}else{
				%>
					<button class="btn btn-secondary" onclick="location.href='NoticeController.do?command=noticelist'">목록</button>
				<%
				}
			%>
		</td>
	</tr> 
</table>
<script type="text/javascript">
	//글삭제하기
	function delBoard(){
		location.href="NoticeController.do?command=muldel&chk=<%=dto.getNo_seq()%>";
		alert("삭제되었습니다");
	}
</script>
</body>
</html>