<%@page import="com.hk.dtos.CalDto"%>
<%@page import="com.hk.utils.Util"%>
<%@include file="header.jsp" %> 
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	tr{
		width: 500px;
	}
</style>
<title></title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js" ></script>
</head>
<%
//요청한 년 , 월 파라미터를 받는다.
	String pYear=request.getParameter("year");//year-1
	String pMonth=request.getParameter("month");//month-1

	//java에서 제공하는 API:  Calendar 객체를 사용
	//추상클래스이기 때문에 new를 쓸 수 없다.
	Calendar cal=Calendar.getInstance();
	int year=cal.get(Calendar.YEAR);//현재 년도를 구함
	int month=cal.get(Calendar.MONTH)+1;//현재 월을 구함(0월~11월)
//	int date=cal.get(Calendar.DATE);
	
	//원하는 년도와 월을 요청했다면 year와 month값을 해당값으로 변경하자
	//현재 년일 때는 요청한 년, 월의 값이 null이어야 한다. 
	if(pYear!=null){//년을 요청했다면.
		year=Integer.parseInt(pYear);
	}
	if(pMonth!=null){//월을 요청했다면.
		month=Integer.parseInt(pMonth);
	}
	
	//과제3
	//문제점: 12월에서 다음달로 넘어갈때 13월... , 년도도 다음년도로 변경
	//       1월에서 전달로 넘어갈때 0월,-1월.... 년도도 전년도로 변경
	if(month>12){
		month=1;
		year++;
	}
	//월 중에 1월 전으로 이동할 경우 month는 12월로, 년도는 전년도로 변경
	if(month<1){
		month=12;
		year--;
	}
	
	//1.현재 달의 1일에 대한 요일을 구하기---> 달력의 처음 시작하는 공백수를 구하기 위함 
	//해당 달의 1일로 Calendar객체를 설정하자
	//month에 -1로 넣고 계산을 해야 0~11로 계산을 할 수 있다. 년 월은 바뀌지만 일은 1일로 같다.
	cal.set(year, month-1, 1);// 2021-09-01로 셋팅한다.
	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);//일(1)~토(7) dayOfWeek-1을 하면 공뱁수를 구할 수 있다.
	System.out.println("현재 요일:"+dayOfWeek);
	
	//2.해당 달의 마지막 날 구하기 --> 마지막날이 28일, 29일, 30일, 31일인지를 알기 위해
	int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	List<JoinUserDto>list=(List<JoinUserDto>)request.getAttribute("list");
	
	JoinUserDto ldto=(JoinUserDto)session.getAttribute("ldto"); 
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
%>
<body>
<h1>관리자 페이지</h1>
<h1>개인 근무표 수정</h1>
<div>
	<span><%=ldto.getId() %></span>님 반갑습니다.
</div> 
<h1>근무표 수정</h1>
<form action="CalController.do" method="post">
<input type="hidden" name="command" value="addschedule" >
<input type="hidden" name="lastday" value="<%=lastDay%>" >
<table border="1" class="table table-hover">
	<caption> 
		<a href="CalController.do?command=insertschedule&year=<%=year-1%>&month=<%=month%>">◁</a>
		<a href="CalController.do?command=insertschedule&year=<%=year%>&month=<%=month-1%>">◀</a>
		<span><%=year%></span>년<span><%=month%></span>월
		<a href="CalController.do?command=insertschedule&year=<%=year%>&month=<%=month+1%>">▶</a>
		<a href="CalController.do?command=insertschedule&year=<%=year+1%>&month=<%=month%>">▷</a>
	</caption>
	<tr style="width: 500px;">
	<th>부서</th>
	<th>직위</th>
	<th>이름</th>
	<%
	for(int i=dayOfWeek;i<lastDay+dayOfWeek;i++){
		String day="";
		if(i%7==6){//6/7 -> 1
			day="금";
		}else if(i%7==0){
			day="토";
		}else if(i%7==1){
			day="일";
		}else if(i%7==2){
			day="월";
		}else if(i%7==3){
			day="화";
		}else if(i%7==4){
			day="수";
		}else if(i%7==5){
			day="목";
		}
		%>
		<th><%=day%></th>
		<%
		
	}
	%>
	</tr>
	<tr>
		<%
		//공백td출력하는 for문
		for(int i=0;i<3;i++){
			out.print("<td>&nbsp;</td>");//out은 jsp의 기본객체중에 하나임
		}
		//날짜td출력하는 for문
		for(int i=1;i<=lastDay;i++){
			%>
			<td class="countA" style="color:<%=Util.fontColor(dayOfWeek,i)%>">
				<%=i%>
			</td>
			<%
		}
		%>
	</tr>
	<tr>
		<td>	
		<select class="custom-select d-block w-100" id="dname" onchange="aa"> 
			<option value="PEDIATRIC" >소아과</option> 
			<option value="NEUROLOGY" >신경과</option> 
			<option value="PLASTIC" >성형회과</option> 
			<option value="EARNOSETHROAT" >이비인후과</option> 
			<option value="ORTHOPEDICS" >정형외과</option> 
			<option value="INTEGRATED" >통합진료과</option> 
			<option value="UROLOGY" >비뇨기과</option> 
		</select> 
		</td>
		<td>
		<select class="custom-select d-block w-100" id="rname" > 		
			<option value="CHIEF">수간호사</option> 
			<option value="RESPONSIBLE">책임간호사</option> 
			<option value="NURSE">평간호사</option> 
		</select> 
		</td>
		<td>
			<span id="n"></span>
			<select class="custom-select d-block w-100" id="rname" > 		
			<%
			for(int i=0;i<list.size();i++){
				JoinUserDto dto=list.get(i);
				if(dto.getDname().equals("PEDIATRIC")&&dto.getRole().equals("NURSE")){
					%>
						<option value="<%=dto.getName()%>"><%=dto.getName() %></option> 
					<%
				}
			}
			%>
			</select> 
		</td>
	</tr>
	<tr>
      <td colspan="<%=lastDay+3%>">
       	 <button class="btn btn-info" type="button" onclick="getName()">조회</button>
         <input class="btn btn-primary" type="submit" value="저장"/>
		 <button class="btn btn-outline-success me-2" type="button" onclick="location.href='CalController.do?command=allschedule'">근무표 보기</button>
      </td>
   </tr>
</table>
</form>
<script type="text/javascript">
	function aa(){
		<%String a="";%>
		document.getElementById("dname").value= <%=a%>
	<%
		System.out.println("하이"+a);
	%>
		document.getElementById("rname").value= <%String b="";%>
	}
</script>
</body>
</html>