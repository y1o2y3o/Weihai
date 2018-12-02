package dao.impl;

import dao.IStudentDao;
import domain.Student;
import java.sql.*;
import util.DBHelper;
/**
 * IStudentDao接口的实现类
 * @author zksfromusa
 *
 */
public class IStudentDaoImpl implements IStudentDao{

	public void add(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "INSERT INTO student(name, id, pwd, level, id_major) VALUES(?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getId());
			stmt.setString(3, student.getPassword());
			stmt.setInt(4, student.getLevel());
			stmt.setString(5, student.getId_major());
			// 执行 sql 语句
			stmt.executeUpdate();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			DBHelper.release(conn, stmt, rs);
		}
	}

	public Student find(String id, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "SELECT * FROM student WHERE id=? AND pwd=? ;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, password);
			// 执行 sql 语句
			rs = stmt.executeQuery();

			
			if(rs.next()){// 找到结果
				Student student = new Student();
				// 设置其他属性
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setPassword(rs.getString("pwd"));
				student.setLevel(rs.getInt("level"));
				student.setId_major(rs.getString("id_major"));
				// 设置主修专业
				sql = "SELECT * FROM major WHERE id=?;";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, student.getId_major());
				rs = stmt.executeQuery();
				rs.next();
				student.setMajor(rs.getString("name"));
				
				return student; // 返回结果对象
			}
			else // 没找到
				return null;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
		finally{
			DBHelper.release(conn, stmt, rs);
		}
	}

	public Student find(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "SELECT * FROM student WHERE id=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			// 执行 sql 语句
			rs = stmt.executeQuery();
			
			if(rs.next()){// 找到结果
				Student student = new Student();
				// 设置其他属性
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setPassword(rs.getString("pwd"));
				student.setLevel(rs.getInt("level"));
				student.setId_major(rs.getString("id_major"));
				// 设置主修专业
				conn = DBHelper.getConnection();
				sql = "SELECT * FROM major WHERE id=?;";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, student.getId_major());
				rs = stmt.executeQuery();
				rs.next();
				student.setMajor(rs.getString("name"));
				
				return student; // 返回结果对象
			}
			else // 没找到
				return null;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
		finally{
			DBHelper.release(conn, stmt, rs);
		}
	}
	
}
