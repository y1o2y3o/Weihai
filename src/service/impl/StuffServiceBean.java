package service.impl;

import java.util.List;


import org.hibernate.Session;
import org.springframework.stereotype.Service;

import service.StuffService;
import util.HibernateUtil;

@Service("stuffService")
public class StuffServiceBean implements StuffService {

	public List getAllStuff() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List result = session.createQuery("from Stuff").list();
		
		session.getTransaction().commit();
		
		return result;
	}


}
