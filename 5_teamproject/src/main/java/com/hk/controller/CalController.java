package com.hk.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

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

@WebServlet("/CalController.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		HttpSession session=request.getSession();
		CalDao cDao=new CalDao();
		JoinUserDao jDao=new JoinUserDao();
		
		if(command.equals("insertschedule")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");			
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";
					
				}
				response.sendRedirect("insertschedule.jsp?year="+year+"&month="+month);
			}
		}else if(command.equals("addschedule")) {
			
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			
			List<JoinUserDto> list=jDao.getPreUserList();
			request.setAttribute("list", list);
			dispatch("addschedule.jsp", request, response); 
			response.sendRedirect("addschedule.jsp?year="+year+"&month="+month+"&date="+date); 
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
