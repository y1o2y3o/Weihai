package service.impl;

import java.util.ArrayList;

import domain.Major;
import domain.Student;
import service.IMajorService;
import dao.IMajorDao;
import dao.impl.*;

public class IMajorServiceImpl implements IMajorService {
	private IMajorDao majorDao = new IMajorDaoImpl();
	public ArrayList<Major> getAllMajors() {
		return majorDao.findAll();
	}
	/**
	 * 按照 id_major 查找 
	 * 返回 Major 对象，若没找到返回null
	 */
	public Major getMajor(String id) {
		return majorDao.find(id);
	}
	
	public static void main(String[] args){
		IMajorService majorService = new IMajorServiceImpl();
		ArrayList<Major> list = majorService.getAllMajors();
		for(Major major:list){
			System.out.println(major.getName());
		}
		Major major = majorService.getMajor(null);
		//System.out.println(major.getId());
	}


}
