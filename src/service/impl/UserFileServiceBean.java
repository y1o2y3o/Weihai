package service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import service.UserFileService;
import util.WebUtils;

import dao.UserFileDao;
import domain.UserFile;

@Service("userFileService")
public class UserFileServiceBean implements UserFileService {
	@Autowired
	private UserFileDao userFileDao;
	
	private boolean login = false;
	
	/* (non-Javadoc)
	 * @see service.impl.UserFileService#upload(domain.UserFile)
	 */
	public boolean upload(UserFile userFile) throws Exception{
		String uid  = userFile.getUid();
		String name = userFile.getName();
		String path = userFile.getPath();
		String type = userFile.getType();
		
		UserFile file = userFileDao.find(uid, name, path, type);
		while (file != null){// 没找到一直循环					
			name = name + "(1)";					
			file = userFileDao.find(uid, name, path, type);
		}
		userFile.setName(name);		
		userFileDao.create(userFile); //默认不hidden
		return true; // 成功上传		
	}
	
	/* (non-Javadoc)
	 * @see service.impl.UserFileService#changeName(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean changeName(String uid, String name, String path, String type, String newName) throws Exception{
		
		UserFile file = userFileDao.find(uid, newName, path, type);
		if(file != null) // 重名文件
			return false;
		
		userFileDao.updateFileName(uid, name, path, type, newName);
		return true;			
	}
	
	public boolean changeName(String fid, String newName) throws Exception{
		
		UserFile file = userFileDao.find(fid);
		UserFile anotherFile = userFileDao.find(file.getUid(), newName, file.getPath(), file.getType());
		if(anotherFile != null) // 重名文件
			return false;
		
		userFileDao.updateFileName(fid, newName);
		return true;			
	}
	
	/* (non-Javadoc)
	 * @see service.impl.UserFileService#remove(java.lang.String)
	 */
	public boolean remove(String fid) throws Exception{
		userFileDao.updateFileValid(fid, false);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see service.impl.UserFileService#delete(java.lang.String)
	 */
	public boolean delete(String fid) throws Exception{
		userFileDao.delete(fid);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see service.impl.UserFileService#getAllFiles(java.lang.String)
	 */
	public List<UserFile> getAllFiles(String uid) throws Exception{	
		return userFileDao.findAll(uid);
	}
	
	/* (non-Javadoc)
	 * @see service.impl.UserFileService#createFloder(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean createFloder(String uid, String path, String name) throws Exception{
		//创建占位文件
		UserFile initFile = new UserFile();
		initFile.setFid(WebUtils.makeId());
		initFile.setName("__init__");
		initFile.setPath(path + name +"/");
		initFile.setType("none");
		initFile.setUid(uid);
		initFile.setUpdateTime(new Date());
		initFile.setValid(true);
		initFile.setHidden(true); //隐藏文件
		if(userFileDao.find(initFile.getUid(), initFile.getName(), initFile.getPath(), initFile.getType())==null){
			userFileDao.create(initFile); // 在数据库中加入隐藏的占位文件
			return true;
		}
		return false;
	}

	/*(non-Javadoc)
	 * @see service.UserFileService#download(java.lang.String)
	 */
	public boolean download(String fid) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * 
		 * 
		 */
		return false;
	}
	
	public UserFile getUserFile(String fid) throws Exception{
		return userFileDao.find(fid);
	}
	
	public boolean restoreUserFile(String fid) throws Exception{
		userFileDao.updateFileValid(fid, true);
		return true;
	}
	
	public boolean deleteFloder(String uid, String path) throws Exception{
		userFileDao.deleteFloder(uid, path);
		return true;
	}
	public boolean isLogin() {
		return login;
	}
	
}
