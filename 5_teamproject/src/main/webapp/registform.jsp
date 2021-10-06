<%@include file="header.jsp" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="input-form-backgroud row"> 
	<div class="input-form col-md-12 mx-auto"> 
	<h1 class="mb-3" style="text-align: center;">회원가입</h1> 
	<form class="validation-form" novalidate> 
		<div class="row">  
			<div class="col-md-6 mb-3"> 
				<label for="name">아이디</label> 
				<input type="text" class="form-control" name="id" placeholder="" required> 
			</div> 
			<br/>
			<div class="col-md-6 mb-3"> 
				<label for="nickname">비밀번호</label> 
				<input type="text" class="form-control" name="pw" placeholder="" required> 
			</div> 
			<br/>
			<div class="col-md-6 mb-3"> 
				<label for="name">이름</label> 
				<input type="text" class="form-control" name="name" placeholder=""required> 
			</div> 
			<br/>
		</div> 
		<div class="mb-3"> 
			<label for="email">이메일</label> 
			<input type="email" class="form-control" name="email" placeholder="you@example.com" required> 
		</div> 
		<br/>
		<div class="mb-3"> 
			<label for="address">주소</label> 
			<input type="text" class="form-control" name="address" placeholder="서울특별시 강남구" required> 
		</div> 
		<br/>
		<div class="row"> 
			<div class="col-md-8 mb-3"> 
				<label for="root">부서</label> 
				<select class="custom-select d-block w-100" name="dname"> 
					<option value=""></option> 
					<option>통합진료과</option> 
					<option>비뇨기과</option> 
				</select> 
			</div> 
			<br/>	
		</div> 
		<hr class="mb-4"> 
		<div class="custom-control custom-checkbox"> 
			<input type="checkbox" class="custom-control-input" id="aggrement" required> 
			<label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label> 
		</div> 
		<div class="mb-4"></div> 
		<button class="btn btn-primary btn-lg btn-block" type="submit">가입 완료</button> 
	</form> 
	</div> 
	</div> 
</div>
</body>
</html> 