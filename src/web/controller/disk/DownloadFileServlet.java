package web.controller.disk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.Student;
import domain.UserFile;
import service.UserFileService;
import util.AppCtx;
import util.WebUtils;

/**
 * Servlet implementation class DownloadFileServlet
 */
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		// 获取session
		HttpSession session = request.getSession();
				
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath("/WEB-INF/user/upload");
		
		//消息提示
		String message = "";
		boolean ok = true;
		try{
			String fileName = request.getParameter("filenameMd5");  //md5
			File file = new File(savePath + "\\" + fileName);
			System.out.println(savePath);
			System.out.println("fname:"+fileName);
			if(!file.exists()){
				throw new Exception();
	        }

	        //设置响应头，控制浏览器下载该文件
			response.addHeader("Content-Length", "" + file.length());
	        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
	        //读取要下载的文件，保存到文件输入流
	        FileInputStream in = new FileInputStream(file);
	        //创建输出流
	        OutputStream out = response.getOutputStream();
	        //创建缓冲区
	        byte buffer[] = new byte[1024];
	        int len = 0;
	        //循环将输入流中的内容读取到缓冲区当中
	        while((len=in.read(buffer))>0){
	            //输出缓冲区的内容到浏览器，实现文件下载
	            out.write(buffer, 0, len);
	        }
	        //关闭文件输入流
	        in.close();
	        //关闭输出流
	        out.close();
		}catch (Exception e) {
			ok = false;
			message= "您要下载的资源已被删除！"+String.format("<meta http-equiv='refresh' content='1;url=%s'/>", 
					request.getContextPath()+"/servlet/ViewDiskServlet?curPath=home/");
			e.printStackTrace();
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
