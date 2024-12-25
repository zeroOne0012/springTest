package com.example.FirstProject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언: 부가 기능을 주입하는 클래스
@Component // IoC 컨테이너가 해당 객체 생성 및 관리
@Slf4j
public class DebuggingAspect {

    // 대상 메소드 선택: CommentService#create*(
//    @Pointcut("execution(* com.example.FirstProject.service.CommentService.create(..))") // *: public, return 타입
    @Pointcut("execution(* com.example.FirstProject.service.CommentService.*(..))") // *: public, return 타입
    private void cut(){

    }

    // 실행 시점 설정: cut()의 대상이 수행되기 이전
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint){ // cut()의 대상 메소드? (정확히 메소드는 아니다)
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        //CommentService#create()의 입력값 => 5
        for(Object obj: args){ //foreach 문
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }

    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, // 대략 cut()의 대상 메소드
                                   Object returnObj) { // 리턴 값 (어노테이션의 returning 값과 일치)
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}
