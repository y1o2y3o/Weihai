package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.UserFileDao;
import domain.UserFile;

public class test {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserFileDao userFileDao = (UserFileDao)ctx.getBean("userFileDao");
	@Test
	public void testCreate() throws Exception{
		UserFile userFile = new UserFile();
		userFile.setFid("2f3");
		userFile.setName("1");
		userFile.setPath("1");
		userFile.setType("1");
		userFile.setUid("1");
		userFile.setUpdateTime(new Date());
		
		userFileDao.create(userFile);
	}

	@Test
	public void testDelete1() throws Exception{		
		userFileDao.delete("sadfsdfsddsf");
	}
	@Test
	public void testDelete2() throws Exception{		
		userFileDao.delete("167","caonima", "safsfsdf", "jpg");
	}
	@Test public void testUpdateName1() throws Exception{
		userFileDao.updateFileName("1", "zhangkaisong");
	}
	@Test public void testUpdateName2() throws Exception{
		userFileDao.updateFileName("1", "1", "1", "1", "zhssdfsfsfdangkaisong");
	}
	@Test public void testFind1() throws Exception{
		UserFile uf=userFileDao.find("1", "1", "1", "1");
		System.out.println(uf);
	}
	@Test public void testFind2() throws Exception{
		UserFile uf=userFileDao.find("21");
		System.out.println(uf);
	}
	@Test public void testFindALl() throws Exception{
		List<UserFile>userFiles = userFileDao.findAll("1");
		System.out.println(userFiles);
	}
	@Test public void testString(){
		String s = "sfsdfsdf";
		System.out.println(s.substring(s.length()).split("/")[0].equals(""));
		System.out.println("!!");
	}
	@Test public void testDeleteFloder() throws Exception{
		userFileDao.deleteFloder("16722076", "home/sss/");
		System.out.println("ssf");
	}
	public static void main(String[] args){
		
	}
}
