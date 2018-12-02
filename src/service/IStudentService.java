package service; 

import domain.Student;

public interface IStudentService {
	/**
	 * 注册学生 成功返回true
	 * @param student
	 * @return
	 */
	abstract boolean RegisterStudent(Student student);
	
	abstract Student LoginStudent(String id, String password);

}
