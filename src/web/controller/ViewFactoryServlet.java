package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Student;
import domain.Stuff;
import service.StuffService;
import service.WorkerService;
import util.AppCtx;

/**
 * Servlet implementation class ViewFactoryServlet
 */
public class ViewFactoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取session
		HttpSession session = request.getSession();
		// 获取stuffservice
		WorkerService wfs = (WorkerService)AppCtx.getWfs();
				
		String message = "";
		try{
			@SuppressWarnings("unchecked")
			List<Student> students = wfs.getAllWorkers(); 
			request.setAttribute("students", students);
			request.getRequestDispatcher("/WEB-INF/pages/student_list.jsp").forward(request, response);	
		}catch(Exception ex){
			ex.printStackTrace();
			message= "！"+String.format("<meta http-equiv='refresh' content='1;url=%s'/>", 
					request.getContextPath()+"/servlet/IndexUIServlet");
			request.setAttribute("msg",message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
