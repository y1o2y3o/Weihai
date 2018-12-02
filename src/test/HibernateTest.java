package test;

import org.hibernate.Session;
import org.junit.Test;

import domain.Stuff;
import util.HibernateUtil;
public class HibernateTest {
	@Test public void TestStuff(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Stuff stuff = new Stuff();
		stuff.setId("1d23");
		stuff.setName("fffff");
		stuff.setWork("jianlaji");
		session.save(stuff);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}
}
