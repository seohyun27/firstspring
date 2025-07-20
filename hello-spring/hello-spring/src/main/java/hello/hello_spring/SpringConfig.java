package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 서비스와 리포지토리의 @Service, @Repository, @Autowired 애노테이션이 없어야 함 -> 이건 컴포넌트 스캔 방식일 때만 필요
// 컨트롤러의 경우는 컴포넌트 스캔을 이용하게 됨 = @Controller + @Autowired

// 자바 코드로 직접 스프링 빈 등록하기
// 스프링 시작 시 @Configuration를 읽고 @Bean에 작성된 클래스들을 빈으로 등록한다

@Configuration
public class SpringConfig {

    @Bean //빈 등록
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //MemberRepository : 인터페이스
        //MemoryMemberRepository : 구현 클래스 -> 여기서 생성 가능

        return new MemoryMemberRepository();
        //이 부분의 구현 클래스만 바꾸면 실제 DB를 연결하는 것이 빠르게 가능하다 -> 수동 연결의 장점
    }
}
