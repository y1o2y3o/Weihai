package web.controller.disk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Student;

import service.UserFileService;
import util.AppCtx;

public class DeleteFloderServlet extends HttpServlet {

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
		// 获取 userFileService 项目 bean
		UserFileService ufs = (UserFileService)AppCtx.getUfs();
		// 获取session
		HttpSession session = request.getSession();
		String path = request.getParameter("inputPath");
		//System.out.println("path "+path);
		//student
		Student st = (Student)session.getAttribute("student");
		//消息提示
		String message = "";
		//System.out.println("inputFid: "+fid);
		try{		
			if(path == null || path.equals("") || !path.endsWith("/"))
				throw new Exception("floder path不合法!");
			if(!ufs.deleteFloder(st.getId(), path))
					throw new Exception("文件夹未找到，或数据库出错");
						
			message= "文件夹子移除成功！"+String.format("<meta http-equiv='refresh' content='0;url=%s'/>", 
					request.getContextPath()+"/servlet/ViewDiskServlet?curPath="+session.getAttribute("curPath"));
		}catch(Exception ex){
			
			message= ex.toString()+String.format("<meta http-equiv='refresh' content='1;url=%s'/>", 
					request.getContextPath()+"/servlet/ViewDiskServlet?curPath="+session.getAttribute("curPath"));
					
			ex.printStackTrace();
		}finally{
			session.removeAttribute("userFiles");
		}
				
		request.setAttribute("msg",message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
