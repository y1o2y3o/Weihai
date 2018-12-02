package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Student;

import service.IShaduleService;
import service.impl.IShaduleServiceImpl;

public class DeleteShaduleServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try{
			int index = Integer.parseInt(request.getParameter("del_index"));
			boolean ok = true;
			if(index >= 0 && index <= 9999){
				IShaduleService service = new IShaduleServiceImpl();
				Student user = (Student) session.getAttribute("student");
		
				if(user != null && service.delShadule(index)){ // 删除成功！
					session.setAttribute("shadule_tip", "成功删除一条shadule！");
				}
				else // 失败
					ok = false;
			}
			else // 失败
				ok = false;
			if(!ok)
				session.setAttribute("shadule_tip", "你删你🐎呢？");
			
			response.sendRedirect(request.getContextPath()+"/servlet/ManageShaduleServlet");
		}
		catch(Exception ex){ // 失败出错
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
