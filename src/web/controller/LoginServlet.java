package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Major;
import domain.Student;

import service.IMajorService;
import service.IStudentService;
import service.impl.IMajorServiceImpl;
import service.impl.IStudentServiceImpl;
import util.WebUtils;
import web.formbean.LoginFormBean;

public class LoginServlet extends HttpServlet {

	/**
	 * 处理登陆表单servlet
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.formbean 承接表单数据
		LoginFormBean formbean = WebUtils.request2Bean(request, LoginFormBean.class);
		//System.out.println("inputName:" + formbean.getInputId());
		
		// 2.验证表单数据
		if(formbean.validate()){ // 表单数据合法
			IStudentService studentService = new IStudentServiceImpl();
			IMajorService majorService = new IMajorServiceImpl();
			Student student = studentService.LoginStudent(formbean.getInputId(), formbean.getInputPassword());
			if(student != null){ // 登陆成功！
				request.getSession().removeAttribute("userFiles");
				Major major = majorService.getMajor(student.getId_major());
				student.setMajor(major.getName());
				
				request.getSession().setAttribute("student", student); // 已登录
				
				response.sendRedirect(request.getContextPath()+"/servlet/IndexUIServlet");
			}
			else{ // 账户或者密码错误
				formbean.getErrors().put("inputId", "提示:学号有问题🐎？");
				formbean.getErrors().put("inputId", "提示:密码有问题🐎？");
				request.setAttribute("formbean", formbean);
				request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			}
		}
		else{ // 输入表单不合法
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
