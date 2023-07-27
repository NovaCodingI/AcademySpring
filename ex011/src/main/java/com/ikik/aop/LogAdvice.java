package com.ikik.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikik.service.LogService;
import com.ikik.vo.LogVO;

import lombok.extern.log4j.Log4j;

/**
 * AOP(Aspect-Oriented Programming)
 * 		관점지향프로그래밍
 * 		핵심비지니스 로직과 부가적인 관심사(기능)를 분리 하여 개발하는 방법론
 * 		
 * 		코드의 중복을 줄이고 유지보수성을 향상 시킬 수 있습니다.
 * 
 *  부가적인 관심사
 *  	로깅, 보안, 트랜젝션관리등
 *  	애플리케이션에서 공통적으로 처리해야 하는 기능
 *  
 *  Aspect
 *  	부가적인 관심사를 모듈화한 단위
 *  	(Advice를 그룹화)Cross Concern : 횡단관심사
 *  	주 업무로직 이외의 부가적인 기능을 의미
 *  
 *  Advice
 *  	부가적인 관심사
 *  
 *  Pointcut
 *  	부가기능이 적용되는 지점
 *  
 *  Target
 *  	핵심 기능을 구현한 객체
 *  	(Core Concern : 핵심관심사)
 *  
 *  Proxy
 *  	Target + Advice
 *  	
 * @author user
 *
 */
@Aspect
@Log4j
@Component
public class LogAdvice {
	
	/**
	 * 포인트컷 : 언제 어디에 적용할 건지 기술
	 * 
	 * Before
	 * 		타겟 객체의 메서드가 실행되기 전에 호출되는 어드바이스
	 * 		JoinPoint를 통해 파라미터 정보 참조 가능
	 */
//	@Before("execution(* com.ikik.service.Board*.*(..))")
//	public void logBefore() {
//		log.info("============================");
//		log.info("============================ 안뜨는거 맞나? Yo");
//		log.info("============================");
//	}
	
	/**
	 * joinPoint
	 * 		타겟에 대한 정보와 상태를 담고 있는 객체로 매개변수로 받아서 사용
	 * @param joinPoint
	 */
	@Before("execution(* com.ikik.service.Reply*.*(..))")
	public void logBeforeParams(JoinPoint joinPoint) {
		log.info("======================= AOP ========================");
		log.info("Param" + Arrays.toString(joinPoint.getArgs()));
		log.info("Target" + joinPoint.getTarget());
		log.info("Method" + joinPoint.getSignature().getName());
		log.info("======================= AOP ========================");
		
	}
	
	/**
	 * Around
	 * 		타겟의 메서드가 호출되기 이전 시점과 이후 시점에 모두 처리해야
	 * 		할 필요가 있는 부가 기능 정의
	 * 		
	 * 		주업무로직을 실행하기 위해 JoinPoint의 하위 클래스인
	 * 		ProceedingJoinPoint타입의 파라메터를 필수적으로 선언해야함
	 * 		ProceedingJoinPoint를 이용해 타겟메서드를 실행하고 결과를 반환 합니다.
	 * 		타겟 메서드의 실행결과를 반환 하기 위해서!!
	 * 
	 * 		(타켓을 감싸고 실행된다)
	 * 
	 * @param pjp
	 * @return
	 */
//	//  ★감싸고 처리하다보니 타겟을 직접 실행해야됩니다 → ProceedingJoinPoint !! : 타겟메서드 실행하는 역할
//	@Around("execution(* com.ikik.service.Board*.*(..))")
//	public Object logTime(ProceedingJoinPoint pjp) {
//		
//		StopWatch stopWatch	= new StopWatch();
//		stopWatch.start();
//		// 컨트롤러가 원하는 값을 담아서 !! 반환 해줘야합니다
//		Object res = "";
//		// 주 업무로직 실행(타겟 메서드의 실행시점을 정할 수 있다)
//		try {
//			res = pjp.proceed();
//			// res 값이 없으면 오류가 발생해요
////			pjp.proceed(); 
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		
//		stopWatch.stop();
//		log.info("===========================");
//		log.info("===========================");
//		log.info(
//				pjp.getTarget().getClass().getName() +"."+ pjp.getSignature().getName()
//				+ " 수행시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초-");
//		log.info("===========================");
//		log.info("===========================");
//		
//		return res;
//	}
	
	@Autowired
	LogService logService;
	
	/**
	 * AfterThrowing
	 * 		타겟 메서드 실행중 예외가 발생한 뒤에 실행할 부가기능
	 * 		오류가 발생내역을 테이블에 등록
	 * 
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut="execution(* com.ikik.service.*.*(..))", throwing="exception")
	public void logException(JoinPoint joinPoint, Exception exception) {
		// 예외가 발생시 예외 내용을 테이블에 저장합니다.
		
		try {
			LogVO vo = new LogVO();
			
			vo.setClassName(joinPoint.getTarget().getClass().getName());
			vo.setMethodName(joinPoint.getSignature().getName());
			vo.setParams(Arrays.toString(joinPoint.getArgs()));
			vo.setErrmsg(exception.getMessage());
			
			logService.insert(vo);
			
			log.info("로그테이블 저장");
		} catch (Exception e) {
			log.info("로그테이블 저장중 예외발생");
			log.info(e.getMessage());
			e.printStackTrace();
		} 
	}
}
