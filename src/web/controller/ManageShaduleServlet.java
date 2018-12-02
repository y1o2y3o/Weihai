package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import util.WebUtils;
import web.formbean.AddShaduleFormBean;
import web.formbean.LoginFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMajorService;
import service.IShaduleService;
import service.impl.IMajorServiceImpl;
import service.impl.IShaduleServiceImpl;
import domain.Major;
import domain.Shadule;
import domain.Student;

public class ManageShaduleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置session 中的专业信息(选课表示要用到)
		HttpSession session = request.getSession();
		if(session.getAttribute("majors") == null){
			IMajorService majorService = new IMajorServiceImpl();
			ArrayList<Major> majors = majorService.getAllMajors();
			session.setAttribute("majors", majors);
		}
		// 设置接受请求格式
		request.setCharacterEncoding("utf-8");
		
		String id_major, scope;	

		try{
			/* 
			 * 1.处理 "查看/编辑课表" 表单 
			 */
			
			
			// 获取选择专业的相关参数
			id_major = request.getParameter("InputId_major");
			scope = request.getParameter("InputScope");
			
			// 验证session中专业数据合法性
			Student student = 
					(Student) session.getAttribute("student"); // 提取session中的学生信息
			// 回传参数到表单
			if(id_major != null) // 表单中有参数
				session.setAttribute("id_major", id_major);
			else if(session.getAttribute("id_major") == null){ // 第一次访问且无参数
				id_major = student.getId_major();
				session.setAttribute("id_major", id_major);
			}
			else{ // 非第一次访问参数存储在session中
				id_major = (String)session.getAttribute("id_major");
			}
			
			if(scope != null) // 表单中有参数
				session.setAttribute("scope", scope);
			else if(session.getAttribute("scope") == null){ // 第一次访且问无参数
				scope = "2018-2019-1";
				session.setAttribute("scope", scope);
			}
			else{ // 非第一次访问参数存储在session中
				scope = (String)session.getAttribute("scope");
			}
			
			/* 
			 * 2.处理 "创建shadule" 表单 
			 */
			if(request.getParameter("token") != null){ // 表单特征参数			
				// 1.formbean 承接表单数据
				AddShaduleFormBean formbean = WebUtils.request2Bean(request, AddShaduleFormBean.class);	
				if(formbean.validate()){ // 表单数据合法
					IShaduleService service = new IShaduleServiceImpl();
					// 接受formbean 数据
					Shadule shadule = new Shadule();
					shadule.setDay(Integer.parseInt(formbean.getInputDay()));
					shadule.setId(formbean.getInputId());
					shadule.setId_major(id_major);
					shadule.setName(formbean.getInputName());
					shadule.setOrder(Integer.parseInt(formbean.getInputOrder()));
					shadule.setPlace(formbean.getInputPlace());
					shadule.setRemark(formbean.getInputRemark());
					shadule.setScope(formbean.getInputScope());
					shadule.setTeacher(formbean.getInputTeacher());
					if(service.addShadule(shadule)){ // 添加 shadule 成功！
						session.setAttribute("shadule_tip", "Succeeded to add a shadule!");
					}
					else{ // 不陈工！
						session.setAttribute("shadule_tip", "Failed to add a shadule!");
					}
				}
				else{
					session.setAttribute("shadule_tip", "Invalid shadule form!");
					System.out.println("表单不合法！");
				}
			}
			
			// 获得并显示相应的课表
			ArrayList<Shadule> shadules = 
					new IShaduleServiceImpl().getShadules(id_major, scope);
			//System.out.println(id_major +":"+scope);
			//System.out.println(shadules.size());
			session.setAttribute("shadules", shadules);
			
			response.sendRedirect(request.getContextPath()+"/servlet/ManageShaduleUIServlet");
		}
		catch(Exception ex){
			ex.printStackTrace();
			String msg = String.format(
					"<h1>对不起！哪里出错了🐎🐎🐎</h1><br>1秒后为您自动跳到主页面！！<meta http-equiv='refresh' content='2;url=%s'/>",
					request.getContextPath()+"/servlet/IndexUIServlet");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}	
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
