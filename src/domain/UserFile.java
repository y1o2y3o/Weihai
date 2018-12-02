package domain;

import java.io.Serializable;
import java.util.Date;

public class UserFile implements Serializable, Comparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3216907821920827790L;
	private String uid; // 文件所有者的id
	private String fid; // 文件唯一识别id
	private String name; // 文件名
	private String path; // 文件虚拟路径
	private String type; //文件类型
	private Date updateTime; // 文件上传时间
	private boolean valid = true; // false表示在回收站
	private boolean hidden = false; // true 表示隐藏文件
	
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public int compareTo(Object o) {
		if(!type.equals(((UserFile)o).type))
			return type.compareTo(((UserFile)o).type);
		return name.compareTo(((UserFile)o).name);
	}
	
	
}
