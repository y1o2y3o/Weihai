package util;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import domain.Stuff;

import service.StuffService;
/**
 * 返回ApplicationContext ctx
 * @author zksfromusa
 *
 */
public class AppCtx {
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static Object ufs= ctx.getBean("userFileService");
	private static Object sfs= ctx.getBean("stuffService");
	private static Object wfs= ctx.getBean("workerService");
	public static ApplicationContext getCtx(){
		return ctx;
	}
	public static Object getUfs(){
		return ufs;
	}
	
	public static Object getSfs(){
		return sfs;
	}
	public static Object getWfs(){
		return wfs;
	}
}
