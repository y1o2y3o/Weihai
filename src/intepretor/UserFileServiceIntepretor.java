package intepretor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.UserFileService;
import service.impl.UserFileServiceBean;

@Component("userFileServiceIntepretor")
@Aspect
public class UserFileServiceIntepretor {
	
	@Autowired
	private UserFileService ufs;
	
	@Pointcut("execution(* service.impl.UserFileServiceBean.*(*,..))")
	private void anyMethod(){}//声明一个切入点
	
//	@Before("anyMethod() && args(name)")
//	public void doAccessCheck(String name){
//		System.out.println("前置通知:" + name);
//	}
//	
//	@AfterReturning(pointcut="anyMethod()", returning="result")
//	public void doXXX(String result){
//		System.out.println("后置通知:" + result);
//	}
//	@After("anyMethod()")
//	public void doFinal(){
//		System.out.println("最终通知");
//	}
//	@AfterThrowing("anyMethod()")
//	public void doException(){
//		System.out.println("例外通知");
//	}
	
	@Around("anyMethod()") //环绕通知
	public Object doBasicProfilling(ProceedingJoinPoint pjp ) throws Throwable{
		Object result = null;	
		result = pjp.proceed();
		return result;	
	}
}
