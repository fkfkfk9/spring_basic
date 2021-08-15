package yoo.springlearn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP로 사용하려면 Aspect를 추가해주어야 한다.
@Aspect
@Component//Component어노테이션을 추가해줌으로써 Bean으로 자동등록된다.
public class TimeTraceAop {

    //AOP를 적용시킬 패키지를 설정해주어야 한다.
    @Around("execution(* yoo.springlearn..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START =========> " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        }finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("END ==========> " + joinPoint.toString() + " " + timeMs + "ms");
        }

        /*
        *   AOP를 사용하기 전에는 컨트롤러가 서비스를 호출할때 실제 서비스를 호출하는 방식으로 의존관계가 성립되었다.
        *   AOP를 적용시키고 나면 프록시라는 가짜 서비스를 만들어 컨트롤러는 프록시를 호출하게 되고 이 프록시에서
        *   joinPoint.proceed를 호출하는 수간 실제 서비스를 호출하게 된다.
        * */
    }
}
