package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
				dispatch("myschedule.jsp", request, response);  
			}
		}else if(command.equals("insertschedule")) {
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
				dispatch("insertschedule.jsp", request, response);  
			}
		}else if(command.equals("addschedule")) {
			String []ids=request.getParameterValues("id");//사람의 수
			String []years=request.getParameterValues("year");
			String []months=request.getParameterValues("month");
			String []dates=request.getParameterValues("date");
			String []wdates=request.getParameterValues("wdate");
			String []works=new String[wdates.length];
			for (int i = 0; i < wdates.length; i++) { 
				works[i]=years[i]+Util.isTwo(months)[i]+Util.isTwo(dates)[i]+wdates[i];
			}
			for (int i = 0; i < works.length; i++) {
				//works에 있는 값은 전체의 값이니 그 값을 id에 맞게 들어가게 해줘야 한다.
				
			}
			//hk (4) : 20211013day 20211013day 20211013day 20211013day 20211013day 20211013day
			//hk2 (1) : 20211013day 20211013day 20211013day 20211013day 20211013day 20211013day
			boolean isS=false;
			for (int i = 0; i < ids.length; i++) {
				String id=ids[i];
				isS=cDao.insertCal(id,works); 
			}
			if(isS) {
				response.sendRedirect("schedule.jsp");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("addnurse")) {
			String[] ids=request.getParameterValues("chk");
			String wdate=request.getParameter("wdate");
			
//			wdate를 바로 넣는 게 아니라 year month date를 받아서 넣어야 한다. 
//			그러므로 id와 wdate를 addschedule로 보내서 처리를 해야 한다.
			response.sendRedirect("addnurseform.jsp");
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
