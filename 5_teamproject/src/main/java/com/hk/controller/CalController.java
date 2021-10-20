package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
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
		
		
		if(command.equals("myschedule")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
				String id=dto.getId();//로그인 한 id 가져오기
				List<String> calList=cDao.getCal(id);//근무날짜(wdate) 리스트 받아오기				
				
				List<JoinUserDto> list=jDao.getPreUserList();
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");			
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}
				request.setAttribute("list", list);
				request.setAttribute("calList", calList);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				dispatch("myschedule.jsp", request, response);  
			}
		}else if(command.equals("insertschedule")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
//				List<JoinUserDto> list=jDao.getPreUserList();
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");	
							
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}
				
				String yyyymm=year+Util.isTwo(month+"");//현재의 근무표 작성 년월
				List<JoinUserDto> list=null;
//				//근무표 작성된 id를 배열로 가져왔다.
				List<String>ids=cDao.getGivenId(yyyymm);
				if(ids.size()==0) {
					list=jDao.getPreUserList();
				}else {
					//근무표 작성된 id를 제외한 JoinUser 리스트를 가져온다.	
					list=jDao.getNotGivenList(ids);				
				}
				request.setAttribute("list", list);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				
				dispatch("insertschedule.jsp", request, response);  
			}
		}else if(command.equals("addschedule")) {
			int lastday=Integer.parseInt(request.getParameter("lastday"));
			String []ids=request.getParameterValues("id");//사람의 수
			String []years=request.getParameterValues("year");
			String []months=request.getParameterValues("month");
			String []dates=request.getParameterValues("date");
			String []wdates=request.getParameterValues("wdate");
			String []works=new String[wdates.length];
			for (int i = 0; i < wdates.length; i++) { 
				works[i]=years[i]+Util.isTwo(months)[i]+Util.isTwo(dates)[i]+wdates[i];
			}
			//hk (4) : 20211013day 20211013day 20211013day 20211013day 20211013day 20211013day
			//hk2 (1) : 20211013day 20211013day 20211013day 20211013day 20211013day 20211013day
			boolean isS=false;
			String []w=null;
			for (int i = 0; i < ids.length; i++) {
				String id=ids[i]; 
				w=Arrays.copyOfRange(works, lastday*i, lastday*(i+1));
				isS=cDao.insertCal(id,w);  					
			}  
		
			if(isS) {
				response.sendRedirect("admin_main.jsp");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("perintschedule")) {
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
				dispatch("perintschedule.jsp", request, response);  
			}
		}else if(command.equals("allschedule")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
				//id와 wdate를 가져온다. list와 calList가 같은 id이면 넣어준다.
				List<CalDto> calList=cDao.getAllList();
				
				List<JoinUserDto> list=jDao.getPreUserList();
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");			
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}
				request.setAttribute("list", list);
				request.setAttribute("calList", calList);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				dispatch("allschedule.jsp", request, response);  
			}
		}else if(command.equals("selectednameall")) {
			
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				String[] dname=request.getParameterValues("chk");	
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				
				//id와 wdate를 가져온다. list와 calList가 같은 id이면 넣어준다.
				List<CalDto> calList=cDao.getAllList();
				 
				List<JoinUserDto> list=jDao.getDnameList(dname);
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");			
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}

				request.setAttribute("list", list);
				request.setAttribute("calList", calList);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				dispatch("allschedule.jsp", request, response);  
			}
		}else if(command.equals("selectednameins")) {
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				JoinUserDto dto=(JoinUserDto)session.getAttribute("ldto");
				String[] chks=request.getParameterValues("chk");	
				List<String>dnames = Arrays.asList(chks);
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");	
							
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";					
				}
				
				String yyyymm=year+Util.isTwo(month+"");//현재의 근무표 작성 년월
//				//근무표 작성된 id를 배열로 가져왔다.
				List<String>ids=cDao.getGivenId(yyyymm);
				List<JoinUserDto> list=null;
				if(ids.size()==0) {
					list=jDao.getPreUserList();
				}else {
	//				//근무표 작성된 id를 제외한 JoinUser 리스트를 가져온다.	
					list=jDao.getIdDnameList(ids,dnames);				
				}
				request.setAttribute("list", list);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				
				dispatch("insertschedule.jsp", request, response);  
			}
		}else if(command.equals("getName")) {			
			String dname=request.getParameter("dname");
			String rname=request.getParameter("rname");
			
			List<JoinUserDto> list=jDao.getName(dname,rname);

			//화면으로 출력하기 ---> $.ajax메서드에서 success: function(obj){} --> obj가 받는다
			//PrintWriter 객체를 통해 print로 출력을 하면 화면에 출력되지 않고 ajax가 출력되는 값을 가로채간다. 
			
			Map<String, List<JoinUserDto> >map=new HashMap<>();
	         map.put("list", list);
	         
	         //map구조: key:value , json구조:key:value
	         JSONObject obj=JSONObject.fromObject(map);//map-->json변환
	         PrintWriter pw=response.getWriter();
	         obj.write(pw);//obj는 프린터가 없어서 프린터기를 빌려줌(pw)
		}else if(command.equals("selectname")) {
			String name = request.getParameter("name");
			String dname = request.getParameter("dname");
			String rname = request.getParameter("rname");
			
			String id=jDao.selectId(name,dname,rname);
			
			System.out.println(name+dname+rname);
			String year=request.getParameter("year");
			String month=request.getParameter("month");			
			if(year==null) {
				Calendar cal=Calendar.getInstance();
				year=cal.get(Calendar.YEAR)+"";
				month=cal.get(Calendar.MONTH)+1+"";					
			}
			
			List<String>calList=cDao.getOwnList(id);
			request.setAttribute("name", name);
			request.setAttribute("calList", calList);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("id", id);
			dispatch("perintschedule.jsp", request, response);
		}else if(command.equals("updateschedule")) {
			String id=request.getParameter("id");
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String []dates=request.getParameterValues("date");
			String []wdates=request.getParameterValues("wdate");
			
			//year+month -> yyyymm
			String yyyymm=year+Util.isTwo(month+"");
//			System.out.println(id+yyyymm);
//			deleteWork(id,yyyymm) ->결과 boolean true -> insert 
			String []works=new String[wdates.length];
			for (int i = 0; i < wdates.length; i++) { 
				works[i]=yyyymm+Util.isTwo(dates)[i]+wdates[i];
			}
			for (int i = 0; i < works.length; i++) {
				System.out.println(works[i]);
			}
			
			boolean isS = cDao.deleteWork(id,yyyymm); 
	         if(isS){ 
	            boolean isIns=cDao.insertCal(id, works);
	            if(isIns) {
	               response.sendRedirect("perintschedule.jsp");               
	            }else{
	                 response.sendRedirect("error.jsp");
	            }
	         }else{
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
