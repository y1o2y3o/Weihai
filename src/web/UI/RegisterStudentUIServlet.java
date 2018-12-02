package web.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMajorService;
import service.impl.IMajorServiceImpl;

import domain.Major;

public class RegisterStudentUIServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("majors") == null){
			IMajorService majorService = new IMajorServiceImpl();
			ArrayList<Major> majors = majorService.getAllMajors();
			request.getSession().setAttribute("majors", majors);
		}	
		request.getRequestDispatcher("/WEB-INF/pages/register_student.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}