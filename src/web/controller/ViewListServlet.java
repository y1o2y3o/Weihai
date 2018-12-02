package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import service.StuffService;
import service.UserFileService;
import util.AppCtx;
import domain.Student;
import domain.Stuff;

public class ViewListServlet extends HttpServlet {

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

		// 获取session
		HttpSession session = request.getSession();
		// 获取stuffservice
		StuffService sfs = (StuffService)AppCtx.getSfs();
		
		String message = "";
		try{
			@SuppressWarnings("unchecked")
			List<Stuff> stuffs = sfs.getAllStuff();
			request.setAttribute("stuffs", stuffs);
			request.getRequestDispatcher("/WEB-INF/pages/stuff_list.jsp").forward(request, response);	
		}catch(Exception ex){
			ex.printStackTrace();
			message= "！"+String.format("<meta http-equiv='refresh' content='1;url=%s'/>", 
					request.getContextPath()+"/servlet/ViewDiskServlet?curPath="+session.getAttribute("curPath"));
			request.setAttribute("msg",message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);	
		}
			
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
