package dao;
import java.util.ArrayList;

import domain.Shadule;
public interface IShaduleDao {
	
	// 两种检索方式
	abstract ArrayList<Shadule> find(String id_major, String scope); // 按照域检索
	abstract Shadule find(int index); // 按照index检索
	
	// 增加一个shadule
	abstract void add(Shadule shadule) throws Exception;
	
	// 删除一个shadule
	abstract void delete(int index) throws Exception;
}
