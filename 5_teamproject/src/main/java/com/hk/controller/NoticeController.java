package com.hk.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.hk.daos.NoticeDao;
import com.hk.dtos.JoinUserDto;
import com.hk.dtos.NoticeDto;

@WebServlet("/NoticeController.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		HttpSession session=request.getSession();
		NoticeDao dao = new NoticeDao();
		
		if(command.equals("admin")) {
			response.sendRedirect("admin_main.jsp");
		}else if(command.equals("addnotice")) {
			response.sendRedirect("addnotice.jsp");
		}else if(command.equals("noticelist")) {
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			String id=ldto.getId();
			String title=request.getParameter("title");
			String content=request.getParameter("content");
		
			boolean isS=dao.insertNotice(new NoticeDto(id,title,content));
			if(isS){ 
				  response.sendRedirect("NoticeController.do?command=getnotice");
				}else{
				  response.sendRedirect("error.jsp");
				}
		}else if(command.equals("getnotice")) {
			List<NoticeDto> list = dao.getNoticeList();
			request.setAttribute("list", list);
			dispatch("notice.jsp",request,response);
		}else if(command.equals("muldel")) {
			String[] chks=request.getParameterValues("chk");
			boolean isS=dao.delNotice(chks);
			if(isS) { 
				response.sendRedirect("NoticeController.do?command=getnotice");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("detailnotice")) {
			String seq=request.getParameter("seq");
			int sseq=Integer.parseInt(seq);
			NoticeDto dto= dao.detailNotice(sseq);
			request.setAttribute("dto", dto);
			
			RequestDispatcher dispatch= request.getRequestDispatcher("detailNotice.jsp");
			dispatch.forward(request, response);
		}else if(command.equals("updateform")) {
			String seq=request.getParameter("seq");
			NoticeDto dto = dao.detailNotice(Integer.parseInt(seq));
			request.setAttribute("dto", dto);
			dispatch("updateNotice.jsp", request, response);
		}else if(command.equals("updatenotice")) {
			String seq=request.getParameter("seq");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			int sseq=Integer.parseInt(seq);
			
			boolean isS=dao.updateNotice(new NoticeDto(sseq,title,content));
			if(isS) {
				response.sendRedirect("NoticeController.do?command=detailnotice&seq="+sseq);
			}else {
				request.setAttribute("msg", "글수정실패");
				dispatch("error.jsp", request, response);
			}
		}
		
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			request.getRequestDispatcher(url).forward(request, response);
		}

}
