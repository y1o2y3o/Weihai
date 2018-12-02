package web.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import util.Identification;

public class LoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/* 用户未登录，给他跳回主页
		 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(!Identification.isLogin((HttpServletRequest)request)){
			String msg = String.format(
					"<h1>你登陆了🐎🐎🐎？</h1><br>1秒后为您自动跳到主页面！！<meta http-equiv='refresh' content='2;url=%s'/>",
					((HttpServletRequest)request).getContextPath()+"/servlet/IndexUIServlet");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			System.out.println("拦截器启动...");
		}
		else
			chain.doFilter(request, response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
