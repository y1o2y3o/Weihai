package web.controller.disk;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.UserFile;

import service.UserFileService;
import util.AppCtx;

public class ClearBinServlet extends HttpServlet {

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
		//防盗链
		String referer = request.getHeader("referer");
		System.out.println(request.getContextPath());
		if(null != referer && referer.trim().indexOf(request.getContextPath() + "/servlet/ViewBinServlet")!=-1){                 
			System.out.println("正常页面请求");                           
		}
		else{           
			System.out.println("盗链");                 
			response.sendRedirect(request.getContextPath() + "/servlet/ViewBinServlet");
			return;
		}
		
		// 获取 userFileService 项目 bean
		UserFileService ufs = (UserFileService)AppCtx.getUfs();
		// 获取session
		HttpSession session = request.getSession();
		//消息提示
        String message = "";
		//System.out.println("inputFid: "+fid);
		try{
			@SuppressWarnings("unchecked")
			List<UserFile> binFiles = (List<UserFile>)session.getAttribute("binFiles");
			String savePath = this.getServletContext().getRealPath("/WEB-INF/user/upload");
			boolean ok = true;
			for(UserFile f: binFiles){
				ufs.delete(f.getFid());		
				if(!DeleteFileServlet.deleteFile(savePath + "\\" + f.getFid()+"." + f.getType()))
					ok = false;
			}
			if(!ok)
				throw new Exception("批量删除存在错误🐎，但不是大问题🐎🐎🐎🐎🐎🐎🐎🐎🐎🐎🐎🐎🐎🐎🐎");
			message= "文件移除成功！"+String.format("<meta http-equiv='refresh' content='0;url=%s'/>", 
                    request.getContextPath()+"/servlet/ViewBinServlet");
		}catch(Exception ex){
			
			message= ex.toString()+String.format("<meta http-equiv='refresh' content='1;url=%s'/>", 
                    request.getContextPath()+"/servlet/ViewBinServlet");
			
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
	/**	 
	 * * 删除文件	 
	 * * 	 
	 * * @param pathname	 
	 * * @return	 
	 * * @throws IOException	 
	 * */	
	public static boolean deleteFile(String pathname){		
		boolean result = false;		
		File file = new File(pathname);		
		if (file.exists()) {			
			file.delete();			
			result = true;			
			System.out.println("文件已经被成功删除");		
		}		
		return result;	
	}
}
