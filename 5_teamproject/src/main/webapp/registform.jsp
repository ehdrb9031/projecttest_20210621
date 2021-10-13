<%@include file="header.jsp" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function idChk(){
		var id=document.getElementsByName("id")[0].value; //입력된 아이디 구하기
		if(id==null||id==""||id==undefined){
			alert("반드시 아이디를 입력하세요");
			document.getElementsByname("id")[0].focus();
		}else{
			open("idchk.jsp?id="+id,"중복체크","width=400px, height=400px");
		}
	}
	
	$(function(){
		$("input[name]").not("[name=id]").focus(function(){
			var idTitle=$("input[name=id]").attr("title");
			if(idTitle=="n"){
				alert("아이디 중복체크를 확인하세요!!");
				document.getElementsByName("id")[0].focus();
			}
		});
		$("input[name=id]").focus(function(){
			$(this).attr("title","n");
		});
	});
</script>
</head>
<body>
<div class="container">
	<div class="input-form-backgroud row"> 
	<div class="input-form col-md-12 mx-auto"> 
	<h1 class="mb-3" style="text-align: center;">회원가입</h1> 
	<form class="validation-form" novalidate action="JoinUserController.do" method="post"> 
	<input type="hidden" name="command" value="after_registform" >
		<div class="row">  
			<div class="col-md-6 mb-3"> 
				<label for="name">아이디</label> 
				<input type="text" class="form-control" name="id" placeholder="" required="required" title="n" autocomplete="off"/> 
				<input type="button" value="중복체크" onclick="idChk()" />
			</div> 
			<br/>
			<div class="col-md-6 mb-3"> 
				<label for="name">이름</label> 
				<input type="text" class="form-control" name="name" placeholder=""required> 
			</div> 
			<br/>
			<div class="col-md-6 mb-3"> 
				<label for="nickname">비밀번호</label> 
				<input type="password" class="form-control" name="pw" placeholder="" required> 
			</div> 
			<br/>
		</div> 
		<div class="mb-3"> 
			<label for="address">주소</label> 
			<input type="text" class="form-control" name="address" placeholder="서울특별시 강남구" required> 
		</div> 
		<br/>
		<div class="mb-3"> 
			<label for="email">이메일</label> 
			<input type="email" class="form-control" name="email" placeholder="you@example.com" required> 
		</div> 
		<br/>
		<div class="mb-3"> 
			<label for="email">핸드폰</label> 
			<input type="text" class="form-control" name="phone" required> 
		</div> 
		<br/>
		<div class="row"> 
			<div class="col-md-8 mb-3"> 
				<label for="root">직급</label> 
				<select class="custom-select d-block w-100" name="role" > 
					<option value="HEAD">간호부장</option> 
					<option value="CHIEF">수간호사</option> 
					<option value="RESPONSIBLE">책임간호사</option> 
					<option value="NURSE">평간호사</option> 
				</select> 
				<label for="root">부서</label> 
				<select class="custom-select d-block w-100" name="dname"> 
					<option value="PEDIATRIC">소아과</option> 
					<option value="NEUROLOGY">신경과</option> 
					<option value="PLASTIC">성형회과</option> 
					<option value="EARNOSETHROAT">이비인후과</option> 
					<option value="ORTHOPEDICS">정형외과</option> 
					<option value="INTEGRATED">통합진료과</option> 
					<option value="UROLOGY">비뇨기과</option> 
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