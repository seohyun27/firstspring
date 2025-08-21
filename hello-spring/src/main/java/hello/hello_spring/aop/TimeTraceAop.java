package hello.hello_spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//AOP와 핵심 서비스 로직을 분리함으로써 코드의 관리가 쉬워짐
//원하는 대상을 선택해 적용 가능

//Spring AOP는 실제 객체(ex. Service)를 그대로 사용하지 않음
//해당 객체를 감싸는 프록시 객체를 만들어서 실제로는 그 프록시가 대신 동작함
//프록시 객체가 존재하기에 기존 코드를 건드리지 않고도 공통 기능을 주입할 수 있음

//AOP를 사용하기 위해 @Aspect 필요
//SpringConfig에서 스프링 빈으로 등록 절차 필요

@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring.service..*(..))")
    //해당 메소드를 어느 메소드에 적용할 것인지 타겟팅
    //hello.hello_spring.service 하위의 모든 패키지에서 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            //원래 호출하려던 메서드(실제 서비스 로직)를 실행 후 해당 결과를 반환
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
