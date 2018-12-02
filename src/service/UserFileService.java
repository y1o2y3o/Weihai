package service;

import java.util.List;

import domain.UserFile;

public interface UserFileService {

	/**
	 * @Description: 上传文件 返回是否成功
	 * @param userFile
	 * @return
	 * @throws Exception 
	 */
	public abstract boolean upload(UserFile userFile) throws Exception;

	/**
	 * @Description: 更改文件名1
	 * @param uid
	 * @param name
	 * @param path
	 * @param type
	 * @param newName
	 * @return
	 * @throws Exception 
	 */
	public abstract boolean changeName(String uid, String name, String path,
			String type, String newName) throws Exception;
	
	/**
	 * @Description: 更改文件名2
	 * @param fid
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	public boolean changeName(String fid, String newName) throws Exception;
	
	/**
	 * @Description: 暂时删除文件
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	public abstract boolean remove(String fid) throws Exception;

	/**
	 * @Description: 永久删除文件
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	public abstract boolean delete(String fid) throws Exception;

	/**
	 * @Description： 得到所有用户文件，若用户没有文件返回空集合
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public abstract List<UserFile> getAllFiles(String uid) throws Exception;

	/**
	 * @Description: 创建文件夹
	 * @param uid
	 * @param path
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public abstract boolean createFloder(String uid, String path, String name)
			throws Exception;
	
	/**
	 * @Description: 下载文件
	 * @param fid
	 * @return
	 */
	public boolean download(String fid);
	/**
	 * @Description: 查找文件
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	public UserFile getUserFile(String fid) throws Exception;
	/**
	 * @Description: 还原文件
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	public boolean restoreUserFile(String fid) throws Exception;
	/**
	 * @Description: 删除一个文件夹
	 * @param uid
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public boolean deleteFloder(String uid, String path) throws Exception;
	public boolean isLogin();
}