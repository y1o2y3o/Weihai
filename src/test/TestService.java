package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import service.StuffService;
import service.UserFileService;
import util.HibernateUtil;

import dao.UserFileDao;
import domain.Stuff;
import domain.UserFile;

public class TestService {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserFileService ufs = (UserFileService)ctx.getBean("userFileService");
	private StuffService sfs = (StuffService)ctx.getBean("stuffService");
	
	@Test
	public void testUpload() throws Exception{
		UserFile userFile = new UserFile();
		userFile.setFid("zffzzz");
		userFile.setName("1");
		userFile.setPath("1");
		userFile.setType("1");
		userFile.setUid("1");
		userFile.setUpdateTime(new Date());
		
		ufs.upload(userFile);
	}
	@Test public void testChangeName() throws Exception{
		ufs.changeName("zffzzz", "zhangkaisog");
	}
	@Test public void testRomove() throws Exception{
		ufs.remove("zffzzz");
	}
	@Test public void testDelete() throws Exception{
		ufs.delete("zffzzz");
	}
	@Test public void testGetAllFiles() throws Exception{
		List<UserFile> files = ufs.getAllFiles("1");
		System.out.println(files);
	}
	@Test public void testCreateFloder() throws Exception{
		ufs.createFloder("1", "home/zks/", "foryou");
	}
	@Test public void batch(){
		
//		String sql = "INSERT INTO userfile(uid, fid, name, path, type, updateTime,) VALUES(?,?,?,?,?,?)";
//		JdbcTemplate jt = (JdbcTemplate)ctx.getBean("jdbcTemplate");
//		BatchPreparedStatementSetter ps = new BatchPreparedStatementSetter() {
//	           //为prepared statement设置参数。这个方法将在整个过程中被调用的次数
//	        public void setValues(PreparedStatement ps, int i) throws SQLException {
//	                ps.setLong(1, i + 10);
//	                ps.setString(2, firstNames.get(i));
//	                ps.setString(3, lastNames.get(i));
//	                ps.setNull(4, Types.TIMESTAMP);
//	                ps.setNull(5, Types.CLOB);
//	              }

//        for(int i = 0; i < 3; i++)
//        	batchArgs.add(new Object[]{"167","fid"+i,"name"+i,"home/","zip",new Date()});
//       
//        jt.batchUpdate(sql, batchArgs);

	}
	@Test public void TestStuffService(){
		@SuppressWarnings("unchecked")
		List<Stuff> stuffs = sfs.getAllStuff();
		for(Stuff sf: stuffs){
			System.out.println("stuff id: " + sf.getId()+"\n"
					+"name: " + sf.getName() + "\n" 
					+ "work: " + sf.getWork());
		}
		HibernateUtil.getSessionFactory().close();
	}
}
