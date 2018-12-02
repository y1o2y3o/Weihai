package dao;

import java.util.ArrayList;

import domain.Major;
import domain.Student;
/**
 * @description: Major类业务逻辑接口
 * @author zksfromusa
 */
public interface IMajorDao {

	abstract Major find(String id);
	
	abstract ArrayList<Major> findAll();
}