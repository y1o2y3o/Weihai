package web.controller.disk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Student;
import domain.UserFile;

import service.UserFileService;
import util.AppCtx;
import util.Identification;

public class ViewBinServlet extends HttpServlet {

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
			// 获取登陆的学生信息
			Student stu = Identification.getStudent(request);
			String uid = stu.getId();
			
			// 若当前session中没有userFiles，就从数据库中取	
			if(session.getAttribute("userFiles") == null){ 
				session.setAttribute("userFiles", ufs.getAllFiles(uid)); 
			}
			
			List<UserFile> binFiles  = new ArrayList<UserFile>(); // binFile 对象
			
			// 获取和验证curPath参数
				// 1.获取curPath
			String curPath = (String)session.getAttribute("curPath");
			//curPath = new String(curPath.getBytes("ISO-8859-1"),"utf-8");
			
			@SuppressWarnings("unchecked")
			List<UserFile> userFiles = (List<UserFile>)session.getAttribute("userFiles"); //从session中获取userFiles
				// 2.验证curPath
			boolean curPathOk = false;
			if(curPath.endsWith("/")){// curPath 合法
				for(UserFile u: userFiles){			
					if(!u.isHidden() && !u.isValid()) // 是回收站中的文件
						binFiles.add(u);
					
					if(u.getPath().startsWith(curPath)) // curPath 合法
						curPathOk = true;
				}			
			}
			
			if(!curPathOk) // curPath不合法！
				throw new Exception("文件路径不合法！");
			
			String lastURL = request.getContextPath() + "/servlet/ViewDiskServlet?curPath="+curPath;
			
			// 设定session内容
			session.setAttribute("binFiles", binFiles); // 设置当前UserFile对象集合
			session.setAttribute("lastURL", lastURL);
//			System.out.println("curPath:"+curPath);
//			System.out.println(curFloderNames);
			// 返回页面
			 request.getRequestDispatcher("/WEB-INF/pages/disk/bin.jsp").forward(request, response);
			return;
		}catch(Exception ex){
			
			message= ex.toString()+String.format("<meta http-equiv='refresh' content='1;url=%s'/>", 
                    request.getContextPath()+"/servlet/IndexUIServlet");
			
			ex.printStackTrace();
			// 出错了！
		}
		
		request.setAttribute("msg",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
