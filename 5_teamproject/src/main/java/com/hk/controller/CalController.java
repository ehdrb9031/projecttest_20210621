package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.CalDao;
import com.hk.daos.JoinUserDao;
import com.hk.dtos.CalDto;
import com.hk.dtos.JoinUserDto;
import com.hk.utils.Util;

import net.sf.json.JSONObject;

@WebServlet("/CalController.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		HttpSession session=request.getSession();
		
		CalDao cDao=new CalDao();
		JoinUserDao jDao=new JoinUserDao();
		
		
		if(command.equals("myschedule")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
				String id=dto.getId();//로그인 한 id 가져오기
				List<String> calList=cDao.getCal(id);//근무날짜(wdate) 리스트 받아오기				
				
				List<JoinUserDto> list=jDao.getPreUserList();
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");			
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}
				request.setAttribute("list", list);
				request.setAttribute("calList", calList);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				dispatch("myschedule.jsp", request, response);  
			}
		}else if(command.equals("insertschedule")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
//				List<JoinUserDto> list=jDao.getPreUserList();
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");	
							
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}
				
				String yyyymm=year+Util.isTwo(month+"");//현재의 근무표 작성 년월
//				//근무표 작성된 id를 배열로 가져왔다.
				List<String>ids=cDao.getGivenId(yyyymm);
//				//근무표 작성된 id를 제외한 JoinUser 리스트를 가져온다.
				
				List<JoinUserDto> list=jDao.getNotGivenList(ids);				
				
				request.setAttribute("list", list);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				
				dispatch("insertschedule.jsp", request, response);  
			}
		}else if(command.equals("addschedule")) {
			int lastday=Integer.parseInt(request.getParameter("lastday"));
			String []ids=request.getParameterValues("id");//사람의 수
			String []years=request.getParameterValues("year");
			String []months=request.getParameterValues("month");
			String []dates=request.getParameterValues("date");
			String []wdates=request.getParameterValues("wdate");
			String []works=new String[wdates.length];
			for (int i = 0; i < wdates.length; i++) { 
				works[i]=years[i]+Util.isTwo(months)[i]+Util.isTwo(dates)[i]+wdates[i];
			}
			//hk (4) : 20211013day 20211013day 20211013day 20211013day 20211013day 20211013day
			//hk2 (1) : 20211013day 20211013day 20211013day 20211013day 20211013day 20211013day
			boolean isS=false;
			String []w=null;
			for (int i = 0; i < ids.length; i++) {
				String id=ids[i]; 
				w=Arrays.copyOfRange(works, lastday*i, lastday*(i+1));
				isS=cDao.insertCal(id,w);  					
			}  
		
			if(isS) {
				response.sendRedirect("admin_main.jsp");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("perintschedule")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
				List<JoinUserDto> list=jDao.getPreUserList();
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");			
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}
				request.setAttribute("list", list);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				dispatch("perintschedule.jsp", request, response);  
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void dispatch(String url, HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		RequestDispatcher dispatch=request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}
