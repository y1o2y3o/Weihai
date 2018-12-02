package service.impl;
import domain.Student;
import service.IStudentService;
import dao.IStudentDao;
import dao.impl.*;

public class IStudentServiceImpl implements IStudentService{
	private IStudentDao studentDao = new IStudentDaoImpl();
	/**
	 * 注册学生
	 */
	public boolean RegisterStudent(Student student) {	
		if(studentDao.find(student.getId()) == null){
			studentDao.add(student);
			return true;
		}
		return false; // 找到了相同一个学生, 注册失败
	}

	/**
	 * 登陆学生
	 */
	public Student LoginStudent(String id, String password) {
		return studentDao.find(id, password);
	}

	public static void main(String[] args){
		Student student = new Student();
		student.setId("12334");
		student.setId_major("1");
		student.setLevel(1234);
		student.setMajor("caonima");
		student.setName("我想奥");
		student.setPassword("123456");
		IStudentService studentService = new IStudentServiceImpl();
		System.out.println(studentService.LoginStudent("1234", "zks"));
	}
}
