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

public class CreateFloderServlet extends HttpServlet {

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
		
		// 获取用户服务
		UserFileService ufs = (UserFileService)AppCtx.getCtx().getBean("userFileService");
		// 获取session
		HttpSession session = request.getSession();
		
		Student st = (Student)session.getAttribute("student");
		String floderName = (String)request.getParameter("floderName");//new String( ()).getBytes("ISO-8859-1"),"UTF-8");
		//System.out.println(floderName);
		String message = "";
		String path = (String)session.getAttribute("curPath");
		try{
			//System.out.println(path);
			if(floderName==null || floderName.equals("") || floderName.indexOf("/")!=-1 )
				throw new Exception("floder名不合法！");
			if(!ufs.createFloder(st.getId(), path, floderName)){
				throw new Exception("floder创建失败！");
			}
			message= "floder创建成功！"+String.format("<meta http-equiv='refresh' content='0;url=%s'/>", 
                    request.getContextPath()+"/servlet/ViewDiskServlet?curPath="+session.getAttribute("curPath"));
		}catch(Exception ex){
			ex.printStackTrace();
			message= "文件名不合法！"+String.format("<meta http-equiv='refresh' content='1;url=%s'/>", 
                    request.getContextPath()+"/servlet/ViewDiskServlet?curPath="+session.getAttribute("curPath"));
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
