package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Student;
import domain.UserFile;

import service.UserFileService;
import util.AppCtx;
import util.Identification;

public class ViewDiskServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		/*用户已登录
		 */
		HttpSession session = request.getSession();
		
		// 获取 userFileService 项目 bean
		UserFileService userFileService = (UserFileService)AppCtx.getUfs();
		
		try{
			// 获取登陆的学生信息
			Student stu = Identification.getStudent(request);
			String uid = stu.getId();
			
			// 若当前session中没有userFiles，就从数据库中取	
			if(session.getAttribute("userFiles") == null){ 
				session.setAttribute("userFiles", userFileService.getAllFiles(uid)); 
			}
			
			
			Set<String> curFloderNames = new TreeSet<String>(); //存放在curpath下的floder名称
			Set<UserFile> curFiles  = new TreeSet<UserFile>(); //存放在curpath下的 UserFile 对象
			
			// 获取和验证curPath参数
				// 1.获取curPath
			String curPath = request.getParameter("curPath");
			//curPath = new String(curPath.getBytes("ISO-8859-1"),"utf-8");
			System.out.println(curPath);
			@SuppressWarnings("unchecked")
			List<UserFile> userFiles = (List<UserFile>)session.getAttribute("userFiles"); //从session中获取userFiles
				// 2.验证curPath
			boolean curPathOk = false;
			if(curPath.endsWith("/")){
				for(UserFile u: userFiles){
					
					String path = u.getPath();
					if(path.startsWith(curPath)){ // u的path包含curPath，说明curPath合法！
						String pathName  = (path.substring(curPath.length())).split("/")[0];
						//System.out.println(pathName);
						if(pathName.equals("")){
							// 是UserFile
							if(!u.isHidden() && u.isValid())
								curFiles.add(u);
						}			
						else {// 是 Floder
							if(u.isValid())
								curFloderNames.add(pathName);
						}
							
						curPathOk = true;
					}
				}
			}
			
			if(!curPathOk) // curPath不合法！
				return;
			
			// 推算出上个文件url路径
			int lastIndex = Math.max("home/".length(), 1 + curPath.substring(0, curPath.length() - 1).lastIndexOf('/'));	
			String lastURL = request.getContextPath() + "/servlet/ViewDiskServlet?curPath="+curPath.substring(0, lastIndex);
			
			// 设定session内容
			session.setAttribute("curFloderNames", curFloderNames); // 设置当前FloderName集合
			session.setAttribute("curFiles", curFiles); // 设置当前UserFile对象集合
			session.setAttribute("curPath", curPath); //设置当前云盘路径
			session.setAttribute("lastURL", lastURL);
//			System.out.println("curPath:"+curPath);
//			System.out.println(curFloderNames);
			// 返回页面
			response.sendRedirect(request.getContextPath()+"/servlet/DiskMainUIServlet");
		}catch(Exception ex){
			ex.printStackTrace();
			// 出错了！
		}
		
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
