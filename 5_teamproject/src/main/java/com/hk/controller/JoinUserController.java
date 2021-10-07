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
		}else if(command.equals("after_registform")){
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String password = request.getParameter("pw");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String role = request.getParameter("role");
			String dname = request.getParameter("dname");
			
			boolean isS = dao.insertUser(new JoinUserDto(id,name,password,address,email,phone,role,dname)); 
			
			if(isS){ 
			  response.sendRedirect("index.jsp");
			}else{
			  response.sendRedirect("error.jsp");
			}
		}else if(command.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else if(command.equals("alluserlist")) {
			List<JoinUserDto> list=dao.getUserList();
			request.setAttribute("list", list);
			dispatch("allUserlist.jsp", request, response);
		}else if(command.equals("muldel")) {
			String[] chks=request.getParameterValues("chk");
			boolean isS=dao.updateList(chks);
			if(isS) { 
				response.sendRedirect("JoinUserController.do?command=alluserlist");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("userlist")) {
			List<JoinUserDto> list=dao.getPreUserList();
			request.setAttribute("list", list);
			dispatch("userlist.jsp", request, response); 
		}else if(command.equals("insertschedule")) {
			response.sendRedirect("CalController.do?command=insertschedule");
		}else if(command.equals("userinfo")) {
			
			String id=request.getParameter("id"); 
	        JoinUserDto dto= dao.getUser(id);   
	        request.setAttribute("dto", dto);
	        dispatch("userinfo.jsp", request, response);

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
