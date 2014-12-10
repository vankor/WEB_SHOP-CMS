package Model;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class MethodDurationAspekt {
	
	@Pointcut("execution(* Model..*(..)) && !execution(* Model.AuthorizeFilter.doFilter(..))")
    public void getPointCutMethods1() {}
	
	@Pointcut("execution(* cc..*(..))")
    public void getPointCutMethods2() {}
	
	@Pointcut("getPointCutMethods1() || getPointCutMethods2()")
    public void getPointCutMethods() {}
	
	
	@Around("getPointCutMethods()")
	private Object getMethodDuration(ProceedingJoinPoint call) throws Throwable{
		
//		System.out.println("FQQQQQ ");
		long t1 = System.nanoTime();
		try{
			return call.proceed();
		}
		finally{
			long t2 = System.nanoTime();
			System.out.println("������������ ���������� ������ "+call.toShortString()+": "+(t2-t1)+" ����������");
		}
		
		
	}

	
}
