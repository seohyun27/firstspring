package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

//순수 Java 코드와 DB 연동을 함께 검사함
//통합 테스트

@SpringBootTest
@Transactional    //각 테스트 메서드 실행 전에 트랜잭션이 시작되고, 테스트 메서드가 끝나면 자동으로 롤백
class MemberServiceIntegrationTest {

    //직접 새 객체를 생성할 필요 없이 스프링 컨테이너에게 memberService와 memberRepository를 받아야 함
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // @Commit : 실행 결과를 롤백 없이 DB에 반영함
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //join에서 중복 회원이 제대로 걸러지는지를 테스트한다
    @Test
    public void duplicateMemberException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //중복된 이름

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
