<%@include file="header.jsp" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인화면</title>
<style type="text/css">
	#container{width: 400px; margin: 200px auto;  }
</style>
</head>
<body>
<div id="container">
	<h1 style="text-align: center;">로그인</h1> 
	<form action="JoinUserController.do" method="post">
		<input type="hidden" name="command" value="login"/>
		<div class="form-group"> 
			<input type="text" class="form-control" placeholder="아이디"  name="id" required="required">
		</div>
		<div class="form-group">
			<input type="password" class="form-control" placeholder="비밀번호" name="pw" maxlength="20" required="required">
		</div>
		<input type="submit" class="btn btn-primary form-control" value="로그인" >
		<input type="button" class="btn btn-success form-control" value="회원가입" onclick="location.href='JoinUserController.do?command=registform'">
	</form>
</div>
</body>
</html>







