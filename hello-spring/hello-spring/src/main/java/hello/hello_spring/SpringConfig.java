package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.attoparser.trace.MarkupTraceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 서비스와 리포지토리의 @Service, @Repository, @Autowired 애노테이션이 없어야 함 -> 이건 컴포넌트 스캔 방식일 때만 필요
// 컨트롤러의 경우는 컴포넌트 스캔을 이용하게 됨 = @Controller + @Autowired

// 자바 코드로 직접 스프링 빈 등록하기
// 스프링 시작 시 @Configuration를 읽고 @Bean에 작성된 클래스들을 빈으로 등록한다

@Configuration
public class SpringConfig {

    /*
    //Jdbc에서 필요
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
     */

    //Jpa에서 필요
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean //빈 등록
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //MemberRepository : 인터페이스
        //MemoryMemberRepository : 구현 클래스 -> 여기서 생성 가능

        //return new MemoryMemberRepository();
        //에플리케이션 조립 부분만 손대면(클래스의 객체화 코드만 바꾸면) DB를 바꾸는 것이 빠르게 가능하다 -> 수동 연결의 장점
        //개방-폐쇄 형식 : 확장에서는 열려 있고 수정/변경에는 닫혀있다
        //스프링의 의존성 주입(DI)를 사용하면 기존의 코드를 손대지 않고 설정만으로 구현 클래스를 변경할 수 있다

        //return new JdbcMemberRepository(dataSource);
        //순수 Jdbc
        //옛날 방식. 중복 코드가 많고 코드 자체가 복잡하다

        //return new JdbcTemplateMemberRepository(dataSource);
        //Jdbc 템플릿
        //Jdbc에 비해 짧고 간결함. 쿼리문을 직접 작성해야 함

        return new JpaMemberRepository(em);
        //JPA
        //DB를 하나의 객체처럼 사용.
        //코드를 짜는 것에 집중 가능. insert와 delete에 쿼리문을 작성할 필요가 없음
    }
}
