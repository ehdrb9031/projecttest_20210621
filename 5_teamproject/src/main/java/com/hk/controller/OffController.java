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

import com.hk.daos.CalDao;
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
		JoinUserDao jDao=new JoinUserDao();
		CalDao cDao=new CalDao();
		
		if(command.equals("changeschedule")) {	
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			if(ldto==null){
				response.sendRedirect("index.jsp");
			} 
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
				response.sendRedirect("OffController.do?command=myoffboardlist");
			}
		}else if(command.equals("myoffboardlist")) {
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			if(ldto==null){
				response.sendRedirect("index.jsp");
			} 
			List<OffDto> list=oDao.getOffList(ldto.getId());//자기 자신이 쓴 리스트
			request.setAttribute("list", list);
			dispatch("myoffboardlist.jsp", request, response);
		}else if(command.equals("selectofflist")) {
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			if(ldto==null){
				response.sendRedirect("index.jsp");
			} 
			
			List<OffDto> list=oDao.getAllOffList(ldto.getId());//모든 신청 리스트			
			
			request.setAttribute("list", list);
			dispatch("selectofflist.jsp", request, response);
		}else if(command.equals("detailoff")) {
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			if(ldto==null){
				response.sendRedirect("index.jsp");
			} 
			String seq=request.getParameter("seq");
			OffDto oDto=oDao.geSelectOff(seq);//모든 신청 리스트	
			JoinUserDto jDto=jDao.getListOne(oDto.getId());
			
			request.setAttribute("oDto", oDto);
			request.setAttribute("jDto", jDto);
			dispatch("detailoff.jsp", request, response);
		}else if(command.equals("muldel")) {
			
			String[] chks=request.getParameterValues("chk");
			boolean isS=oDao.delOffList(chks);
			if(isS) { 
				response.sendRedirect("OffController.do?command=selectofflist");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("offYes")) {
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			if(ldto==null){
				response.sendRedirect("index.jsp");
			} 
			String seq=request.getParameter("seq");
			String id=request.getParameter("id");
			String wDate=request.getParameter("wdate");//근무날짜
			String oDate=request.getParameter("odate");//변경날짜
			System.out.println(id+oDate+wDate);
			boolean isS=oDao.updateOffYes(seq);
			
			if(isS) {  
				//승인을 눌렀을 때 odate를 cal DB wdate에 넣어주면 된다.
				boolean isUp=cDao.updateWdate(id,wDate,oDate);
				if(isUp) {
					response.sendRedirect("OffController.do?command=selectofflist");
				}else {
					response.sendRedirect("error.jsp");
				}
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("offNo")) {
			String seq=request.getParameter("seq");
			boolean isS=oDao.updateOffNo(seq);
			if(isS) { 
				response.sendRedirect("OffController.do?command=selectofflist");
			}else {
				response.sendRedirect("error.jsp");
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
