package com.hk.controller;

import java.io.IOException;
import java.util.Date;

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
		if(command.equals("mychangelist")) {
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			if(ldto==null){
				response.sendRedirect("index.jsp");
			} 
			String id=request.getParameter("id");
			
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String dwork=request.getParameter("dwork"); //근무 날짜	
			String work=year+Util.isTwo(month)+Util.isTwo(date)+dwork;
			
			System.out.println(work);
			
//			Date odate=request.getParameter("odate"); //변경 날짜
			String wdate=request.getParameter("owork"); //근무 날짜	
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
