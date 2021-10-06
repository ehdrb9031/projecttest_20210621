package com.hk.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.JoinUserDao;
import com.hk.dtos.JoinUserDto;
 
@WebServlet("/JoinUserController.do")
public class JoinUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		HttpSession session=request.getSession();
		JoinUserDao dao=new JoinUserDao();
		
		if(command.equals("login")) {
			String id=request.getParameter("id");
			String password=request.getParameter("pw");
			
			JoinUserDto ldto=dao.getLogin(id, password);
			
			if(ldto==null||ldto.getId()==null) {
				String msg="로그인 오류입니다.";
				response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "uft-8"));
			}else {
				session.setAttribute("ldto", ldto);
				session.setMaxInactiveInterval(10*60);
				
				if(ldto.getRole().toUpperCase().equals("ADMIN")) {
					response.sendRedirect("admin_main.jsp");
				}else if(ldto.getRole().toUpperCase().equals("MANAGER")) {
					response.sendRedirect("user_main.jsp");
				}else if(ldto.getRole().toUpperCase().equals("USER")) {
					response.sendRedirect("user_main.jsp");
				}
			}
		}else if(command.equals("registform")) {
			response.sendRedirect("registform.jsp");
		}else if(command.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else if(command.equals("alluserlist")) {
			List<JoinUserDto> list=dao.getUserList();
			request.setAttribute("list", list);
			dispatch("allUserlist.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			request.getRequestDispatcher(url).forward(request, response);
		}

}
