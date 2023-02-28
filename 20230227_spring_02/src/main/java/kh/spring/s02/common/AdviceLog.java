package kh.spring.s02.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;


@Service
@Aspect
public class AdviceLog {
	
	private static final Logger logger = LoggerFactory.getLogger(AdviceLog.class); 
	
	
	
	// * 1개 이상이 존재함. 뭐든 상관없다.
	// .. 0개 이상이 존재함. 
	@Pointcut("execution(public * kh.spring.s02..*Controller.*(..) )")
	public void controllerPointCut() {}
	// 위 pointcut의 이름은 "controllerPointCut" 이름을 지정하기위해서 만들어진 메소드임으로 구현부는 딱히 없음 
	// 어노테이션을 위해서 메소드가 쓰임
	
	
	
	@Pointcut("execution(public * kh.spring.s02..*Dao.*(..) )")
	public void daoPointCut() {}
	
	@Pointcut("execution(public * kh.spring.s02..*ServiceImp1.*(..) )")
	public void ServicePointCut() {}
	
	@Around("controllerPointCut()")
	public Object aroundControlerPointCut(ProceedingJoinPoint pjp) throws Throwable{
		
		Object returnObj = null;
		
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.println("args[" + i + "]: " + args[i] );
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		returnObj = pjp.proceed();
		stopwatch.stop();
		logger.info("Ctrl Return[" + stopwatch.getTotalTimeMillis() + "]: " + returnObj);
		return returnObj;
	}
	
	@Around("daoPointCut()")
	public Object aroundDaoPointCut(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			logger.info("args[" + i + "]: " + args[i] );
		} 
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 타켓 메소드 실행 
		returnObj = pjp.proceed();
		stopwatch.stop();
		
		logger.info("DAO Return[" + stopwatch.getTotalTimeMillis() + "]: " + returnObj);
		return returnObj;
	}
	
	@Around("ServicePointCut()")
	public Object aroundServicePointCut(ProceedingJoinPoint pjp) throws Throwable{
		
		Object returnObj = null;
		
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.println("args[" + i + "]: " + args[i] );
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		returnObj = pjp.proceed();
		stopwatch.stop();
		logger.info("Srv Return[" + stopwatch.getTotalTimeMillis() + "]: " + returnObj);
		return returnObj;
	}
	
}
