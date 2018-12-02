package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;
import dao.IMajorDao;
import domain.Major;
import domain.Student;

public class IMajorDaoImpl implements IMajorDao{

	public Major find(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "SELECT * FROM major WHERE id=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			// 执行 sql 语句
			rs = stmt.executeQuery();
			// 释放数据库连接
			
			if(rs.next()){// 找到结果
				Major major = new Major();
				major.setId(rs.getString("id"));
				major.setName(rs.getString("name"));
				major.setFrom(rs.getString("from"));
				return major;
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

	public ArrayList<Major> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Major> list = new ArrayList<Major>();
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "SELECT * FROM major;";
			stmt = conn.prepareStatement(sql);
			// 执行 sql 语句
			rs = stmt.executeQuery();
			// 释放数据库连接
			
			while(rs.next()){// 找到结果
				Major major = new Major();
				major.setId(rs.getString("id"));
				major.setName(rs.getString("name"));
				major.setFrom(rs.getString("from"));
				list.add(major);
			}
			return list;
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
