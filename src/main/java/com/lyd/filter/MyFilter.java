package com.lyd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.pojo.User;
	/**
	 * 
	 * @author Young
	 * @description   该过滤器用于非登陆请求下的一切请求,若session域中的User 对象为空 则一切请求都 重定向至 index.html
	 * @date          2018年1月3日 下午2:51:42 
	 *
	 */
public class MyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest request=(HttpServletRequest) req;
			
			HttpServletResponse response=(HttpServletResponse) res;
			
			String url=request.getRequestURL().toString();
			//如果请求的是 index页面则 放行 对所有静态资源放行
			if(url.contains("index")||url.contains("alert")||
			   url.contains("css")||url.contains("images")||url.contains(".js")||url.contains("login")&&!url.contains("jsp")) {
				
				chain.doFilter(request, response);
				return ;
			}
			HttpSession session = request.getSession();
			
			User user =(User) session.getAttribute("USER");
			
			if(user==null) {
			   response.sendRedirect("index.html");
			   return ;
			}
			//放行
			chain.doFilter(request, response);
			
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
