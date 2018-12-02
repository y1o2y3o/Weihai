package web.controller.disk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserFileService;
import util.AppCtx;

public class RemoveFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取 userFileService 项目 bean
		UserFileService ufs = (UserFileService)AppCtx.getUfs();
		// 获取session
		HttpSession session = request.getSession();
		String fid = request.getParameter("inputFid");
		//消息提示
        String message = "";
		//System.out.println("inputFid: "+fid);
		try{
			if(fid == null || fid.equals(""))
				throw new Exception("文件fid不合法!");
			if(!ufs.remove(fid))
				throw new Exception("文件fid未找到，或数据库出错");
			
			message= "文件移除成功！"+String.format("<meta http-equiv='refresh' content='0;url=%s'/>", 
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
