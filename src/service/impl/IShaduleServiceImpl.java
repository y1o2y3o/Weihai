package service.impl;

import java.util.ArrayList;

import dao.IShaduleDao;
import dao.impl.IShaduleDaoImpl;
import domain.Shadule;
import service.IShaduleService;

public class IShaduleServiceImpl implements IShaduleService{

	/**
	 * 得到一个完整的的课表
	 */
	public ArrayList<Shadule> getShadules(String id_major, String scope) throws Exception {
		ArrayList<Shadule> shadules = 
				new IShaduleDaoImpl().find(id_major, scope);
		if( shadules != null)
			return shadules;
		else
			throw new Exception("参数错误！！");
	}

	/**
	 * 增加一个shadule
	 * @return true表示成功
	 */
	public boolean addShadule(Shadule shadule) {
		IShaduleDao dao = new IShaduleDaoImpl();
		try{
			dao.add(shadule);
			return true;
		}
		catch (Exception ex){
			return false;
		}
	}

	/**
	 * 删除一个shadule
	 * @return true表示成功
	 */
	public boolean delShadule(int index) {
		IShaduleDao dao = new IShaduleDaoImpl();
		try{
			if(dao.find(index) != null){ //找到了！
				dao.delete(index);
				return true;
			}
			return false; //删除失败！
		}
		catch (Exception ex){
			return false; // 删除失败！
		}
	}
	
	
	public static void main(String[] args){
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
		
		IShaduleService service = new IShaduleServiceImpl();
		System.out.println(service.addShadule(shadule));
		System.out.println(service.delShadule(5));
		
	}
}
