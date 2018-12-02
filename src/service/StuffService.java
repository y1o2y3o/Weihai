package service;

import java.util.List;

import org.springframework.stereotype.Service;
import util.HibernateUtil;

public interface StuffService {
	/**
	 * @Description: 得到所有的stuff信息
	 * @return
	 */
	List getAllStuff();
}	
