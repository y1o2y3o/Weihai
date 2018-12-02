package service.impl;

import java.util.List;


import org.hibernate.Session;
import org.springframework.stereotype.Service;

import service.StuffService;
import service.WorkerService;
import util.HibernateUtil;

@Service("workerService")
public class WorkerServiceBean implements WorkerService {

	public List getAllWorkers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List result = session.createQuery("from Student").list();
		
		session.getTransaction().commit();
		
		return result;
	}


}
