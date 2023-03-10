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
	
	
	// * 1개이상이 존재함. 
	// .. 0개이상이 존재
	@Pointcut("execution(public * kh.spring.s02..*Controller.*(..))")
	public void controllerPointCut() {}
	// 위 pointcut의 이름은  "controllerPointCut()"
	
	@Pointcut("execution(public * kh.spring.s02..*Dao.*(..))")
	public void daoPointCut() {}
	
	@Pointcut("execution(public * kh.spring.s02..*ServiceImpl.*(..))")
	public void serviceImplPointCut() {}
	
	@Around("controllerPointCut()")
	public Object aroundControllerPointCut(ProceedingJoinPoint pjp) throws Throwable{
		Object returnObj = null;
		
		logger.info("▶Ctrl: " + pjp.getThis() + " " +pjp.getSignature().getName());
		
		
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			logger.info("▶args["+i+"]: "+ args[i]);
		}
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 타겟메소드 실행
		returnObj = pjp.proceed();
		stopwatch.stop();
		
		logger.info("▶Ctrl Return["+stopwatch.getTotalTimeMillis()+"]: "+ returnObj);
		return returnObj;
//		System.out.println("컨트롤러 모든 메소드가 호출되면 해당메소드(타겟메소드)가 실행되기 "
//				+ " 전 Before 바로 이 메소드(beforeControllerPointCut)를 실행하고  "
//				+ " 컨트롤러의 해당메소드(타겟메소드)로 가서 실행됨.");
	}
	@Around("serviceImplPointCut()")
	public Object aroundServiceImplPointCut(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		
		logger.info("▶Srvc: " + pjp.getThis() + " " +pjp.getSignature().getName());
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			logger.info("▶▶args["+i+"]: "+ args[i]);
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 타겟메소드 실행
		returnObj = pjp.proceed();
		stopwatch.stop();
		
		logger.info("▶▶Srvc Return["+stopwatch.getTotalTimeMillis()+"]: "+ returnObj);
		return returnObj;
	}
	@Around("daoPointCut()")
	public Object aroundDaoPointCut(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		logger.info("▶DAO: " + pjp.getThis() + " " + pjp.getSignature().getName());
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			logger.info("▶▶▶args["+i+"]: "+ args[i]);
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 타겟메소드 실행
		returnObj = pjp.proceed();
		stopwatch.stop();
		
		logger.info("▶▶▶DAO Return["+stopwatch.getTotalTimeMillis()+"]: "+ returnObj);
		return returnObj;
	}
	
}
