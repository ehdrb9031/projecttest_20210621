package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
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
		
		
		if(command.equals("insertschedule")) {
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
			
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String yyyyMMdd=year+Util.isTwo(month)+Util.isTwo(date);//"20211013"
			String wdate=yyyyMMdd+request.getParameter("wdate");
			String seq=request.getParameter("seq");
		
			//seq값을 통해 id를 구해온다.
			String id=jDao.getUserId(seq);
			
			//totalcal DB에 넣어준다.
			boolean isS=cDao.insertCal(new CalDto(id,wdate));
			if(isS) {
				response.sendRedirect("JoinUserController.do?command=admin_main");
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
