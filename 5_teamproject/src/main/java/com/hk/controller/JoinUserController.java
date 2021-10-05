package com.hk.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.daos.JoinUserDao;
import com.hk.dtos.JoinUserDto;
 
@WebServlet("/JoinUserController.do")
public class JoinUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		
		JoinUserDao dao=new JoinUserDao();
		
		if(command.equals("login")) {
			String id=request.getParameter("id");
			String password=request.getParameter("pw");
			
			JoinUserDto ldto=dao.getLogin(id, password);
			
			if(ldto==null||ldto.getId()==null) {
				String msg="로그인 오류입니다.";
				response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "uft-8"));
			}else {
				if(ldto.getRole().toUpperCase().equals("ADMIN")) {
					response.sendRedirect("admin_main.jsp");
				}else if(ldto.getRole().toUpperCase().equals("MANAGER")) {
					response.sendRedirect("user_main.jsp");
				}else if(ldto.getRole().toUpperCase().equals("USER")) {
					response.sendRedirect("user_main.jsp");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
