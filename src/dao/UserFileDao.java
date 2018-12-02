package dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import domain.UserFile;
import exception.UserFileNotFoundException;

public interface UserFileDao {
	
	/**
	 * @Description: 创建一个用户文件
	 * @param userFile
	 * @throws Exception 
	 */
	void create(UserFile userFile ) throws Exception;
	
	/**
	 * @Description: 删除文件1
	 * @param fid
	 * @param name
	 * @param path
	 * @param type
	 * @throws Exception 
	 */
	void delete(String uid, String name, String path, String type) throws Exception;
	
	/**
	 * @Description: 删除文件2
	 * @param fid
	 * @throws Exception 
	 */
	void delete(String fid) throws Exception;
	
	/**
	 * @Description: 改变 userfile 的文件名1
	 * @param uid
	 * @param name
	 * @param path
	 * @param type
	 * @param newName
	 * @throws Exception 
	 */
	void updateFileName(String uid, String name, String path, String type, String newName) throws Exception;
	
	/**
	 * @Description: 改变 userfile 的文件名2
	 * @param fid
	 * @param newName
	 * @throws Exception 
	 */
	void updateFileName(String fid, String newName) throws Exception;
	/**
	 * @Description: 查找 userfile 1
	 * @param uid
	 * @param name
	 * @param path
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	UserFile find(String uid, String name, String path, String type) throws Exception;
	
	/**
	 * @Description: 查找 userfile 2
	 * @param fid
	 * @return
	 * @throws Exception 
	 */
	UserFile find(String fid) throws Exception;
	
	/**
	 * @Description: 返回一个用户所有userfile
	 * @param uid
	 * @return
	 * @throws Exception 
	 */
	List<UserFile> findAll(String uid) throws Exception;
	
	/**
	 * @Description: 更新valid
	 * @param fid
	 * @param v
	 * @throws Exception
	 */
	public void updateFileValid(String fid, boolean v) throws Exception;
	
	/**
	 * @Description: 删除文件夹，把其中的文件移入回收站
	 * @param uid
	 * @param path
	 * @throws Exception
	 */
	public void deleteFloder(String uid, String path) throws Exception;
}