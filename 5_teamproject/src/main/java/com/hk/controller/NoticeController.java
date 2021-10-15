package com.hk.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

		if(command.equals("addnotice")) {//admin 메인 페이지에서 공지사항 등록으로 이동
			response.sendRedirect("addnotice.jsp");
		}else if(command.equals("insertnotice")) {//addnotice에서 글쓰기 -> notice
			JoinUserDto ldto = (JoinUserDto)session.getAttribute("ldto");
			String id=ldto.getId();
			String title=request.getParameter("title");
			String content=request.getParameter("content");

			boolean isS=dao.insertNotice(new NoticeDto(id,title,content));
			if(isS){ 
				List<NoticeDto> list = dao.getNoticeList();
				request.setAttribute("list", list);
				dispatch("notice.jsp",request,response); 
			}else{
				request.setAttribute("msg", "글수정실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("noticelist")) {		
			session.removeAttribute("readcount");
			
			List<NoticeDto> list = dao.getNoticeList();
			request.setAttribute("list", list);
			dispatch("notice.jsp",request,response);
		}else if(command.equals("muldel")) {//삭제
			String[] chks=request.getParameterValues("chk");
			boolean isS=dao.delNotice(chks);
			if(isS) { 
				response.sendRedirect("notice.jsp");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("detailnotice")) {//notice페이지에서 detailnotice로 이동
			int seq=Integer.parseInt(request.getParameter("seq"));
			NoticeDto dto= dao.detailNotice(seq);
			if(session.getAttribute("readcount")==null) {
				session.setAttribute("readcount", "readcount");
				dao.readCount(seq);
			}

			request.setAttribute("dto", dto);
			dispatch("detailNotice.jsp",request, response);
		}else if(command.equals("updateform")) {//detailNotice -> 수정버튼
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
	
	//원하는 쿠키 구하는 메서드
	public Cookie getCookie(String cookieName, HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		Cookie cookie=null;
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(cookieName)) {//쿠키 하나에 대해 key:value의 형태로 저장되어 있다.
				cookie=cookies[i];
			}
		}		
		return cookie;
	}

}
