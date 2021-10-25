<%@page import="com.hk.utils.Util"%>
<%@page import="com.hk.dtos.JoinUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp" %> 
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
#a{
	font-size: 20pt;
}
#b{
	font-size: 17pt;
}
</style>
</head>
<%
	JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
	if(ldto==null){
		pageContext.forward("index.jsp");
	}
	
	String year=(String)request.getAttribute("year");
	String month=(String)request.getAttribute("month");
	String date=(String)request.getAttribute("date");
	String wdate=(String)request.getAttribute("wdate");
%>
<body>
<div id="tablebox">
<h1>근무표 변경 신청</h1>
   <form action="OffController.do" method="post">
      <input type="hidden" name="command" value="mychangelist"> 
      <input type="hidden" name="id" value="<%=ldto.getId()%>" >
      <input type="hidden" name="year" value="<%=year%>" >
      <input type="hidden" name="month" value="<%=month%>" >
      <input type="hidden" name="date" value="<%=date%>" >
      <input type="hidden" name="dwork" value="<%=wdate%>" >
      <table class="table table-hover">
         <tr>
            <th>작성자</th>
            <td><input type="text" value="<%=ldto.getName()%>" readonly="readonly"></td>
         </tr>
         <tr> 
            <th>근무날짜</th>
            <td><input type="text" value="<%=year%>-<%=month%>-<%=date%>-<%=Util.transWork(wdate)%>" readonly="readonly"></td>
         </tr>
         <tr>
            <th>변경날짜</th>
            <td>
            	<input type="date" name="odate" min="<%=year%>-<%=month%>-<%=date%>" max="<%=year%>-<%=month+1%>-<%=date%>" required="required">
	            <select class="custom-select d-block w-100" name="owork" > 
						<option value="day">데이</option> 
						<option value="eve">이브</option> 
						<option value="night">나이트</option> 
						<option value="off">오프</option> 
				</select> 
			</td>
         </tr>
         <tr>
            <th>사유</th>
            <td><input name="title" required="required" type="text"  class="form-control"></td>
         </tr>
         <tr>
            <th>사유 내용</th>
            <td><textarea  name="content" required="required" rows="20" cols="60" class="form-control"></textarea></td>
         </tr>
         <tr>
            <td colspan="2">
               <input type="submit" value="등록" class="btn btn-primary">
               <input type="button" value="취소" class="btn btn-secondary"
               onclick="location.href='CalController.do?command=myschedule'"/>
            </td>
         </tr>
      </table>
   </form>
</div>
</body>
</html>