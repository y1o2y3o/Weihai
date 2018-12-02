package dao.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.UserFileDao;
import domain.UserFile;
import exception.*;

@Repository("userFileDao")
public class UserFileDaoBean implements UserFileDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @Description: 创建一个用户文件
	 * @param userFile
	 * @throws Exception 
	 */
	public void create(UserFile userFile) throws Exception{
		try{
			//数据库创建文件记录
			String sql = "insert into userfile(uid, fid, name, path, type, updateTime, hidden) values(?, ?, ?, ?, ?, ?, ?)";
			int num = jdbcTemplate.update(sql, new Object[]{userFile.getUid(),
										 userFile.getFid(),
										 userFile.getName(),
										 userFile.getPath(),
										 userFile.getType(),
										 userFile.getUpdateTime(), userFile.isHidden()});
			if(num <= 0)
				throw new RuntimeException("Can not create UserFile.");
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("Can not create UserFile.");
		}		
	}
	
	/**
	 * @Description: 删除文件1
	 * @param fid
	 * @param name
	 * @param path
	 * @param type
	 * @throws Exception 
	 */
	public void delete(String uid, String name, String path, String type) throws Exception{
		try{
			String sql = "delete from userfile where uid=? and name=? and path=? and type=?";
			int num = jdbcTemplate.update(sql, new Object[]{uid, name, path, type});
			if(num <= 0 )
				throw new Exception("Can not delete UserFile.");
		}	
		catch(Exception ex){
			throw new Exception("Can not delete UserFile.");
		}
	}
	
	/**
	 * @Description: 删除文件2
	 * @param fid
	 * @throws Exception 
	 */
	public void delete(String fid) throws Exception{
		try{
			String sql = "delete from userfile where fid=?";
			int num = jdbcTemplate.update(sql, fid);
			if(num <= 0 )
				throw new Exception("Can not delete UserFile.");
		}	
		catch(Exception ex){
			throw new Exception("Can not delete UserFile.");
		}
	}
	
	/**
	 * @Description: 改变 userfile 的文件名1
	 * @param uid
	 * @param name
	 * @param path
	 * @param type
	 * @param newName
	 * @throws Exception 
	 */
	public void updateFileName(String uid, String name, String path, String type, String newName) throws Exception{
		try{
			String sql = "update userfile set name=? where uid=? and name=? and path=? and type=?";
			int num = jdbcTemplate.update(sql, new Object[]{newName, uid, name, path, type});
			if(num <= 0 )
				throw new Exception("Can not update UserFile.");
		}
		catch(Exception ex){
			throw new Exception("Can not update UserFile.");
		}
	}
	
	/**
	 * @Description: 改变 userfile 的文件名2
	 * @param fid
	 * @param newName
	 * @throws Exception 
	 */
	public void updateFileName(String fid, String newName) throws Exception{
		try{
			String sql = "update userfile set name=? where fid=?";
			int num = jdbcTemplate.update(sql, new Object[]{newName, fid});
			if(num <= 0 )
				throw new Exception("Can not update UserFile.");
		}
		catch(Exception ex){
			throw new Exception("Can not update UserFile.");
		}
	}
	/**
	 * @Description: 查找 userfile 1， 没找到返回null
	 * @param uid
	 * @param name
	 * @param path
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public UserFile find(String uid, String name, String path, String type) throws Exception{
		UserFile userFile = new UserFile();
		try{
			// 查询userfile
			String sql = "select * from userfile where uid=? and name=? and path=? and type=?";
			RowMapper<UserFile> rowMapper = new BeanPropertyRowMapper<UserFile>(UserFile.class);
	        List<UserFile> users = jdbcTemplate.query(sql, rowMapper, new Object[]{uid, name, path, type});
	        
	        if(users.size() == 0) // 没找到
	        	return null;
	        return users.get(0);
		}catch(Exception ex){
			throw new Exception("Can not find userfile.");
		}		
	}
	
	/**
	 * @Description: 查找 userfile 2, 没找到返回null
	 * @param fid
	 * @return
	 * @throws Exception 
	 */
	public UserFile find(String fid) throws Exception{
		UserFile userFile = new UserFile();
		try{
			// 查询userfile
			String sql = "select * from userfile where fid=?";
			RowMapper<UserFile> rowMapper = new BeanPropertyRowMapper<UserFile>(UserFile.class);
	        List<UserFile> users = jdbcTemplate.query(sql, rowMapper, fid);
	        
	        if(users == null) // 没找到
	        	return null;
	        
	        return users.get(0);
		}catch(Exception ex){
			throw new Exception("Can not find userfile.");
		}
		
	}
	
	/**
	 * @Description: 返回一个用户所有userfile
	 * @param uid
	 * @return
	 * @throws Exception 
	 */
	public List<UserFile> findAll(String uid) throws Exception{
		try{
			// 查询userfile
			String sql = "select * from userfile where uid=?";
			RowMapper<UserFile> rowMapper = new BeanPropertyRowMapper<UserFile>(UserFile.class);
	        List<UserFile> users = jdbcTemplate.query(sql, rowMapper, uid);
	        
	        return users;
		}catch(Exception ex){
			throw new Exception("Can not find all userfile.");
		}
		
	}
	
	/**
	 * @Description: 更新valid
	 * @param fid
	 * @param v
	 * @throws Exception
	 */
	public void updateFileValid(String fid, boolean v) throws Exception{
		try{
			String sql = "update userfile set valid=? where fid=?";
			int num = jdbcTemplate.update(sql, new Object[]{v, fid});
			if(num <= 0 )
				throw new Exception("Can not update UserFile valid.");
		}
		catch(Exception ex){
			throw new Exception("Can not update UserFile valid.");
		}
	}
	
	/**
	 * @Description: 删除文件夹，把其中的文件移入回收站
	 * @param uid
	 * @param path
	 * @throws Exception
	 */
	public void deleteFloder(String uid, String path) throws Exception{
		try{

			String sql = "update userfile set valid=0 where uid=? and path like '"+path+"%'";
			int num = jdbcTemplate.update(sql, new Object[]{uid});
			String sql2 = "delete from userfile where name=?  and uid=? and type=? and path like '"+path+"%' and path<>'home/'";
			int num2 = jdbcTemplate.update(sql2, new Object[]{"__init__",uid,"none"});
			if(num <= 0)
				throw new Exception("Can not update UserFile valid.");
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("Can not delete floder.");
		}
	}
}
