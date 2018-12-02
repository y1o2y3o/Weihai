package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @description:学生实体类
 * @author zksfromusa
 *
 */
@Entity
public class Student implements Serializable{
	private String name;
	@Id
	private String id;
	@Column(name="pwd")
	private String password;
	private int level;
	@Transient
	private String major; // 主修专业
	@Column(name="id_major")
	private String id_major; // 外键id_major
	
	/* Setters and Getters */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setId_major(String id_major){
		this.id_major = id_major;
	}
	public String getId_major(){
		return id_major;
	}
}
