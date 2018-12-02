package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.Student;

public class Identification {
	public static boolean isLogin(HttpServletRequest request){
		try{
			HttpSession se = request.getSession();
			Student st = (Student) se.getAttribute("student");
			st.getId();
			return true; //验证通过
		}catch(Exception ex){
			return false; // 验证不通过！
		}
	}
	
	public static Student getStudent(HttpServletRequest request){
		return (Student)request.getSession().getAttribute("student"); 
	}
}
