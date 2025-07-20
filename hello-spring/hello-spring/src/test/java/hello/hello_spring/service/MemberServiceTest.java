package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
// import static org.junit.jupiter.api.Assertions.*;에서
// fail() 메소드를 인식하지 못하므로 필요한 부분을 명시적으로 import함

class MemberServiceTest {
    //테스트 코드는 빌드시 실제 코드에 포함되지 않는다
    //즉, 메소드의 이름을 한글로 작성하거나 해도 크게 문제가 되지 않는다

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //테스트가 실행하기 전마다 각각 생성된다
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    //많은 테스트 메소드들은 아래 세 가지 경우로 쪼개진다 -> 나눠 작성하면 가독성 up
    //given : 테스트를 위한 기반 데이터
    //when : 검증하려 하는 것
    //then : 결과

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member); //서비스에서 가입->해당 멤버가 적절하다면 저장소에 저장됨

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /**
     * join에서 중복 회원이 제대로 걸러지는지를 테스트한다
     */
    @Test
    public void duplicateMemberException(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //중복된 이름

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //memberService.join(member2)를 실행하면 IllegalSateException이 터져야 한다
        //예외 메시지를 반환받을 수도 있음
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2); //중복된 이름에서 예외가 발생해야 함
            fail(); //예외가 발생하지 않았음으로 테스트는 실패가 된다
        } catch (IllegalStateException e) {
            //예외가 발생하며 중복된 이름이 정상적으로 걸러졌다
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}