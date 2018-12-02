package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import util.AppCtx;
import util.WebUtils;
import web.formbean.*;
import service.impl.IStudentServiceImpl;
import service.impl.IMajorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Major;
import domain.Student;
import service.impl.IStudentServiceImpl;
import service.IMajorService;
import service.IStudentService;
import service.UserFileService;

public class RegisterStudentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 把参数封装到formbean中
		request.setCharacterEncoding("UTF-8");
		RegisterStudentFormBean formbean = new RegisterStudentFormBean();
		formbean.setInputId(request.getParameter("InputId"));
		formbean.setInputId_major(request.getParameter("InputId_major"));
		formbean.setInputLevel(request.getParameter("InputLevel"));
		formbean.setInputName(request.getParameter("InputName"));
		formbean.setInputPassword(request.getParameter("InputPassword"));
		formbean.setInputPassword2(request.getParameter("InputPassword2"));
		
		// 设置session 中的专业信息
		HttpSession session = request.getSession();
		if(session.getAttribute("majors") == null){
			IMajorService majorService = new IMajorServiceImpl();
			ArrayList<Major> majors = majorService.getAllMajors();
			request.getSession().setAttribute("majors", majors);
		}	
		
		try{
			if(formbean.validate()){ // 表单合法
				// 登记学生信息
				Student student = new Student();
				student.setId(formbean.getInputId());
				student.setId_major(formbean.getInputId_major());
				student.setLevel(Integer.parseInt(formbean.getInputLevel()));
				student.setName(formbean.getInputName());
				student.setPassword(formbean.getInputPassword());
				
				// 注册学生信息
				IStudentService service = new IStudentServiceImpl();
				if(service.RegisterStudent(student)){ //学生成功注册
					// 获取用户服务
					UserFileService ufs = (UserFileService)AppCtx.getCtx().getBean("userFileService");
					ufs.createFloder(student.getId(), "", "home");
					String msg = String.format(
							"<h1>恭喜您，成功注册学籍！🐎🐎🐎</h1><br>3秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='3;url=%s'/>",
							request.getContextPath()+"/servlet/LoginUIServlet");
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/message.jsp").forward(request, response);
				}
				else{
					formbean.getErrors().put("InputName", "注册用户已存在！！");
		            request.setAttribute("formbean", formbean);
		            request.getRequestDispatcher("/WEB-INF/pages/register_student.jsp").forward(request, response);
				}
			}
			else{// 表单提交不合法！
				request.setAttribute("formbean", formbean);
				request.getRequestDispatcher("/WEB-INF/pages/register_student.jsp").forward(request, response);
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
			response.getWriter().write("对不起，注册失败！");
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
