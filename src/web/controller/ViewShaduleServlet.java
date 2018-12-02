package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMajorService;
import service.impl.IMajorServiceImpl;
import service.impl.IShaduleServiceImpl;

import domain.Major;
import domain.Shadule;
import domain.Student;
/**
 * 查看课表的servlet
 * @author zksfromusa
 *
 */
public class ViewShaduleServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
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
		
		// 设置session 中的专业信息(选课表示要用到)
		HttpSession session = request.getSession();
		if(session.getAttribute("majors") == null){
			IMajorService majorService = new IMajorServiceImpl();
			ArrayList<Major> majors = majorService.getAllMajors();
			request.getSession().setAttribute("majors", majors);
		}
		
		try{
			String id_major, scope;	
			// 获取表单参数
			id_major = request.getParameter("InputId_major");
			scope = request.getParameter("InputScope");
			
			// 验证合法性
			Student student = 
					(Student) request.getSession().getAttribute("student"); // 提取session中的学生信息
			if(id_major == null)
				id_major = student.getId_major();
			if(scope == null)
				scope = "2018-2019-1";
			
			// 获得相应的课表
			ArrayList<Shadule> shadules = 
					new IShaduleServiceImpl().getShadules(id_major, scope);
			request.setAttribute("shadules", shadules);
			
			request.getRequestDispatcher("/WEB-INF/pages/shadule/view.jsp").forward(request, response);
		}
		catch(Exception ex){
			ex.printStackTrace();
			String msg = String.format(
					"<h1>对不起！哪里出错了🐎🐎🐎</h1><br>1秒后为您自动跳到主页面！！<meta http-equiv='refresh' content='2;url=%s'/>",
					request.getContextPath()+"/servlet/IndexUIServlet");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
