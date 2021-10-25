<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.JoinUserDao"%>
<%@include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
.box {
	margin: 2px 2px;
	width: 100%;
	heigjt: 50%;
	padding: 5px;
	background-color: lightgray;
}

.btnbox {
	/* /*    버튼박스  */ */ object align :"left";
	width: 100%;
	margin: 1px solid black;
	padding: 10px;
	text-align: center;
}

.button {
	height: 80px;
	width: 200px;
	font-size: 16px;
	background-color: white;
	margin: 3px;
}

h1 {
	text-align: center;
	font-size: 35px;
	margin: 40px;
	font-weight: bold;
}

.admin {
	text-align: center;
	background-color: white;
	font-size: 15px;
	heght: 8%;
	margin: 20px;
}

.user {
	text-align: center;
	background-color: white;
	font-size: 15px;
	margin: 10px;
	color: blue;
}

/*   로그아웃 */
a {
	color: red;
	position: fixed;
	right: 70px;
	font-size: 15px;
}

body {
	background-color: white;
}

#absolute {
	position: absolute;
	left: 230px;
	bottom: -150px;
}

#parent {
	position: relative;
	width: 100px;
	height: 100px;
	background: skyblue;
}

#child {
	position: absolute;
	right: 0;
	left: 650px;
	font-size: 10px;
	bottom: -35px;
}

h3 {
	font-size: 20px;
}
</style>
</head>
<%
	JoinUserDto ldto=(JoinUserDto)session.getAttribute("ldto"); 
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
%>
<body>

	<a href="JoinUserController.do?command=logout">로그아웃</a>
	<h1>Administrator Page</h1>
	<div class="admin">
		<span class="user"><%=ldto.getId() %></span>님 반갑습니다.
	</div>

	<div class="box">
		<nav class="navbar navbar-light bg-light">
			<form class="btnbox">
				<button class="button" type="button"
					onclick="location.href='JoinUserController.do?command=alluserlist'">간호사전체조회</button>
				<button class="button" type="button"
					onclick="location.href='JoinUserController.do?command=userlist'">재직간호사조회</button>
				<button class="button" type="button"
					onclick="location.href='CalController.do?command=allschedule'">근무표</button>
				<button class="button" type="button"
					onclick="location.href='CalController.do?command=perintschedule'">개인근무표작성</button>
				<button class="button" type="button"
					onclick="location.href='OffController.do?command=selectofflist'">휴가/근무변경조회</button>
				<button class="button" type="button"
					onclick="location.href='NoticeController.do?command=noticelist'">공지사항목록</button>
				<button class="button" type="button"
					onclick="location.href='JoinUserController.do?command=userinfo'">나의정보</button>
			</form>
		</nav>
		<div>
			<div id="absolute">
				<img src="image/nurse.jpg" alt="이미지" width="420" height="500px">
			</div>

			<div id="child">
				<h3>나는 일생을 의롭게 살며 전문간호직에 최선을 다할 것을 하느님과 여러분 앞에 선서합니다.</h3>
				<h3>나는 인간의 생명에 해로운 일은 어떤 상황에서도 하지 않겠습니다.</h3>
				<h3>나는 간호의 수준을 높이기 위하여 전력을 다하겠으며,</h3>
				<h3>간호하면서 알게된 개인이나 가족의 사정은 비밀로 하겠습니다.</h3>
				<h3>나는 성심으로 보건의료인과 협조하겠으며,</h3>
				<h3>나의 간호를 받는 사람들의 안녕을 위하여 헌신하겠습니다.</h3>
			</div>
		</div>
	</div>
</body>
</html>
