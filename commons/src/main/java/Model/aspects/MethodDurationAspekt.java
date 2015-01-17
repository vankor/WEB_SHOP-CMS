package Model.aspects;

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
	
	@Pointcut("execution(* Model..*(..)) && !execution(* Model.others.AuthorizeFilter.doFilter(..))")
    public void getPointCutMethods1() {}
	
	@Pointcut("execution(* cc..*(..))")
    public void getPointCutMethods2() {}
	
	@Pointcut("getPointCutMethods1() || getPointCutMethods2()")
    public void getPointCutMethods() {}
	
	
	@Around("getPointCutMethods()")
	private Object getMethodDuration(ProceedingJoinPoint call) throws Throwable{
		

		long t1 = System.nanoTime();
		Object output = new Object();
			output = call.proceed();
			long t2 = System.nanoTime();
			System.out.println("ƒлительность выполнени€ метода "+call.toShortString()+": "+(t2-t1)+" наносекунд");
		
		return output;
		
		
	}

	
}
