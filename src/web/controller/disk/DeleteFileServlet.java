package web.controller.disk;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.UserFile;

import service.UserFileService;
import util.AppCtx;

public class DeleteFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取 userFileService 项目 bean
		UserFileService ufs = (UserFileService)AppCtx.getUfs();
		// 获取session
		HttpSession session = request.getSession();
		String fid = request.getParameter("inputFid");
		String type = request.getParameter("inputType");
		//消息提示
        String message = "";
		//System.out.println("inputFid: "+fid);
		try{
			if(fid == null || fid.equals("")||type==null||type.equals(""))
				throw new Exception("文件fid不合法!");
			if(!ufs.delete(fid))
				throw new Exception("文件fid未找到，或数据库出错");
			
			String savePath = this.getServletContext().getRealPath("/WEB-INF/user/upload");
			if(!DeleteFileServlet.deleteFile(savePath + "\\" + fid+"." + type))
				throw new Exception("文件实体不存在！null");
			
			message= "文件删除成功！"+String.format("<meta http-equiv='refresh' content='0;url=%s'/>", 
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
