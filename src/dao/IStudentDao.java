package dao;

import domain.Student;
/**
 * @description: 学生类业务逻辑接口
 * @author zksfromusa
 */
public interface IStudentDao {
	/**
	 * 添加
	 * @param student
	 */
	abstract void add(Student student);
	
	/**
	 * 查找1
	 * @param id
	 * @param password
	 * @return 
	 */
	abstract Student find(String id, String password);
	
	/**
	 * 查找2
	 * @param id
	 * @param password
	 * @return 
	 */
	abstract Student find(String id);
}
