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
//	System.out.println("현재 요일:"+dayOfWeek);
	
	//2.해당 달의 마지막 날 구하기 --> 마지막날이 28일, 29일, 30일, 31일인지를 알기 위해
	int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	//한달 단위 일정 목록
	List<CalDto>list=(List<CalDto>)request.getAttribute("list");

	List<String>calList=(List<String>)request.getAttribute("calList");
	
	JoinUserDto ldto=(JoinUserDto)session.getAttribute("ldto"); 
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
%>
<body>
<h1>간호사 페이지</h1>
<h1>나의 근무표 조회</h1>
<div>
	<span><%=ldto.getName() %></span>님 반갑습니다.
</div> 
<table border="1" class="table table-hover">
	<caption> 
		<a href="CalController.do?command=myschedule&year=<%=year-1%>&month=<%=month%>">◁</a>
		<a href="CalController.do?command=myschedule&year=<%=year%>&month=<%=month-1%>">◀</a>
		<span><%=year%></span>년<span><%=month%></span>월
		<a href="CalController.do?command=myschedule&year=<%=year%>&month=<%=month+1%>">▶</a>
		<a href="CalController.do?command=myschedule&year=<%=year+1%>&month=<%=month%>">▷</a>
	</caption>
	<tr style="width: 500px;">
		<th style="color:red">일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th style="color:blue">토</th>
	</tr>
	<tr>
		<%
		//공백td출력하는 for문
		for(int i=0;i<dayOfWeek-1;i++){
			out.print("<td>&nbsp;</td>");//out은 jsp의 기본객체중에 하나임
		}
		//날짜td출력하는 for문
		for(int i=1;i<=lastDay;i++){
			%>
			<td>
				<a class="countA" style="color:<%=Util.fontColor(dayOfWeek,i)%>"
				href="OffController.do?command=changeschedule&year=<%=year%>&month=<%=month%>&date=<%=i%>&wdate=<%=Util.getCalView(calList,year,month,i) %>"><%=i%></a>
				<div style="font-size: 15px;">
					<%=Util.getCalView(calList,year,month,i) %>			
				</div>
			</td>
			<%
			// (현재날짜+공백수)%7==0 조건을 만족하는 요일은 토요일이다.
			if((i+dayOfWeek-1)%7==0){//1주가 끝난 것
				out.print("</tr><tr>");//tr을 통해 줄바꿈을 할 수 있다.
			}
		}
		//달력의 나머지 공백수 출력하는 for문
		int nbsp=(7-(lastDay+dayOfWeek-1)%7)%7;//나누어 떨어지는 달력일 때 한 칸 더 생기는 현상을 방지하기 위해 %7을 한 번 더 해서 처리를 해준다.
		for(int i=1;i<=nbsp;i++){
			out.print("<td>"+i+"</td>");
		}
		
		%>
	</tr>
	<tr>
		<td colspan="7">
			<button>근무변경신청</button>
			<button class="btn btn-info" type="button" onclick="location.href='JoinUserController.do?command=main&id=<%=ldto.getId()%>'">메인</button>
		</td>
	</tr>
</table>
<%! //자바 메서드 선언부
	//요일별 날짜 색깔 적용하기
	public String fontColor(int dayOfWeek,int i){
		String color="";
		if((i+dayOfWeek-1)%7==0){//토요일
			color="blue";
		}else if((i+dayOfWeek-1)%7==1){//일요일
			color="red";
		}else{//평일
			color="black";
		}
		return color;
	}
%>
</body>
</html>