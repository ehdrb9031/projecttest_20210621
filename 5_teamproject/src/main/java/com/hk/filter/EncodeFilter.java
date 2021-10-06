package com.hk.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "utf-8")
		})
public class EncodeFilter implements Filter {

	public FilterConfig config;
	
    public void init(FilterConfig fConfig) throws ServletException {
    	//initParam의 FilterConfig fConfig를 통해 초기값을 가져올 수 있다.
    	config=fConfig;
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//작업을 수행하는 핵심 코드
		//chain.doFilter(request, response)를 기준으로 앞에 작성하면 요청 뒤에 작성하면 응답
		//초기값 접근을 할 수 없다. init이 접근을 해서 가져올 수 있어 전역변수를 통해 가져온다. 
		String encoding=config.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);//인코딩 처리
		response.setContentType("text/html;charset="+encoding);
		//요청관련 코드
		chain.doFilter(request, response);
		//응답관련 코드
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
