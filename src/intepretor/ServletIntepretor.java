package intepretor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import service.UserFileService;

@Component("servletIntepretor")
@Aspect
public class ServletIntepretor {	
	
	@Pointcut("execution(* web.controller.*.*(..))")
	private void anyMethod(){}//声明一个切入点
	
//		@Before("anyMethod() && args(name)")
//		public void doAccessCheck(String name){
//			System.out.println("前置通知:" + name);
//		}
//	
//		@AfterReturning(pointcut="anyMethod()", returning="result")
//		public void doXXX(String result){
//			System.out.println("后置通知:" + result);
//		}
//		@After("anyMethod()")
//		public void doFinal(){
//			System.out.println("最终通知");
//		}
//		@AfterThrowing("anyMethod()")
//		public void doException(){
//			System.out.println("例外通知");
//		}
	
	@Around("anyMethod()") //环绕通知
	public Object doBasicProfilling(ProceedingJoinPoint pjp) throws Throwable{
		Object result = null;	
		System.out.println("request: ");
		result = pjp.proceed();
		return result;	
	}	

}
