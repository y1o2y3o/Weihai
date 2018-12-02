package dao.impl;
import java.util.ArrayList;

import dao.IShaduleDao;
import domain.Shadule;
import java.sql.*;

import util.DBHelper;

public class IShaduleDaoImpl implements IShaduleDao{

	/**
	 * find 接口实现
	 * 返回一个课表
	 * @param:（专业，学期）
	 */
	public ArrayList<Shadule> find(String id_major, String scope) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Shadule> shadules = new ArrayList<Shadule>();
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "SELECT * FROM shadule WHERE id_major=? AND scope=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id_major);
			stmt.setString(2, scope);
			// 执行 sql 语句
			rs = stmt.executeQuery();
			// 遍历结果集
			while(rs.next()){
				Shadule shadule = new Shadule();
				shadule.setDay(rs.getInt("day"));
				shadule.setId(rs.getString("id"));
				shadule.setId_major(rs.getString("id_major"));
				shadule.setIndex(rs.getInt("index"));
				shadule.setOrder(rs.getInt("ord"));
				shadule.setPlace(rs.getString("place"));
				shadule.setRemark(rs.getString("remark"));
				shadule.setScope(rs.getString("scope"));
				shadule.setTeacher(rs.getString("teacher"));
				shadule.setName(rs.getString("name"));
				shadules.add(shadule);
			}
			return shadules;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
		finally{
			DBHelper.release(conn, stmt, rs);
		}
	}

	/**
	 * add接口实现
	 * 增加一个shadule
	 * 不成功就throw Exception
	 * @throws Exception 
	 */
	public void add(Shadule shadule) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = 
			"INSERT INTO shadule(id, name, teacher, place, remark, id_major, scope, day, ord) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, shadule.getId());
			stmt.setString(2, shadule.getName());
			stmt.setString(3, shadule.getTeacher());
			stmt.setString(4, shadule.getPlace());
			stmt.setString(5, shadule.getRemark());
			stmt.setString(6, shadule.getId_major());
			stmt.setString(7, shadule.getScope());
			stmt.setInt(8, shadule.getDay());
			stmt.setInt(9, shadule.getOrder());
			// 执行 sql 语句
			int num = stmt.executeUpdate();
			// 遍历结果集
			if(num<=0)
				throw new Exception("Can not add a shadule!");
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new Exception("Runtime Error when adding a shadule!!");
		}
		finally{
			DBHelper.release(conn, stmt, rs);
		}
	}
	/**
	 * @method:find(按照index 查找shadule)
	 * 找不到返回null
	 */
	public Shadule find(int index) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "SELECT * FROM shadule WHERE shadule.index=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, index);
			// 执行 sql 语句
			rs = stmt.executeQuery();
			// 遍历结果集
			if(rs.next()){ //找到了
				Shadule shadule = new Shadule();
				shadule.setDay(rs.getInt("day"));
				shadule.setId(rs.getString("id"));
				shadule.setId_major(rs.getString("id_major"));
				shadule.setIndex(rs.getInt("index"));
				shadule.setOrder(rs.getInt("ord"));
				shadule.setPlace(rs.getString("place"));
				shadule.setRemark(rs.getString("remark"));
				shadule.setScope(rs.getString("scope"));
				shadule.setTeacher(rs.getString("teacher"));
				shadule.setName(rs.getString("name"));
				return shadule;
			}
			return null; // 没找到
		}
		catch (Exception ex){ // 出错也返回null
			ex.printStackTrace();
			return null;
		}
		finally{
			DBHelper.release(conn, stmt, rs);
		}
	}

	public void delete(int index) throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 建立数据库连接
			conn = DBHelper.getConnection();
			// 准备 sql 语句
			String sql = "DELETE FROM shadule WHERE shadule.index=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, index);
			// 执行 sql 语句
			int num = stmt.executeUpdate();
			// 遍历结果集
			if(num <= 0)
				throw new Exception("没找到删除的记录！！");
		}
		catch (Exception ex){ // 出错也返回null
			ex.printStackTrace();
			throw new Exception("RunTime Error when deleting!!");
		}
		finally{
			DBHelper.release(conn, stmt, rs);
		}
	}
	
	public static void main(String[] args) throws Exception{
		Shadule shadule = new Shadule();
		shadule.setDay(1); // 表示星期一
		shadule.setId("SCC-120");
		shadule.setId_major("1");
		shadule.setOrder(1); // 早上第一节
		shadule.setPlace("SX906");
		shadule.setRemark("1602");
		shadule.setScope("2018-2019-2");
		shadule.setTeacher("gaoties father");
		shadule.setName("ruanjianshebaoyuanli");
		
		IShaduleDao shaduleDao = new IShaduleDaoImpl();
		shaduleDao.add(shadule);
		Shadule shadule2 = shaduleDao.find(2);
		shaduleDao.delete(2);
		System.out.println("ok");
	}

}
