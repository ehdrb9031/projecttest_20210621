package com.hk.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.hk.daos.NoticeDao;

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
		}else if(command.equals("addnoticeform")) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
