package com.hk.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.JoinUserDao;
import com.hk.daos.OffDao;
import com.hk.dtos.JoinUserDto;
import com.hk.dtos.OffDto;
import com.hk.utils.Util;

@WebServlet("/OffController.do")
public class OffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		HttpSession session=request.getSession();
		OffDao oDao=new OffDao();
		if(command.equals("changeschedule")) {			
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String wdate=request.getParameter("wdate");
			
			
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
			request.setAttribute("wdate", wdate);
			
			dispatch("changeschedule.jsp", request, response);  
		}else if(command.equals("mychangelist")) {
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			if(ldto==null){
				response.sendRedirect("index.jsp");
			} 
			String id=request.getParameter("id");
			
			String dYear=request.getParameter("year");
			String dMonth=request.getParameter("month");
			String dDate=request.getParameter("date");
			String dWork=request.getParameter("dwork"); //근무 날짜	
			String wdate=dYear+Util.isTwo(dMonth)+Util.isTwo(dDate)+dWork;			
		 
			String oDate=request.getParameter("odate"); //변경 날짜
			String oWork=request.getParameter("owork"); //근무 날짜	
			String off_title=request.getParameter("title");
			String off_content=request.getParameter("content");
			
			String odate=oDate.substring(0, 4)+oDate.substring(5, 7)+oDate.substring(8, 10)+oWork;
		
			boolean isS=oDao.insertOff(new OffDto(id,off_title,off_content,wdate,odate));
			if(isS) {
				response.sendRedirect("OffController.do?command=myoffboardlist&id="+id);
			}
		}else if(command.equals("myoffboardlist")) {
			String id=request.getParameter("id");
			List<OffDto> list=oDao.getOffList(id);
			request.setAttribute("list", list);
			dispatch("myoffboardlist.jsp", request, response);
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
