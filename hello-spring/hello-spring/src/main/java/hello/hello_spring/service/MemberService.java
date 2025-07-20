package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//만들어진 도메인들을 이용해 구현되는 실제 서비스 로직

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //서비스 객체가 자신이 의존하는 리포지토리 객체의 구현체를 직접 생성하지 않고
    //외부에서 전달받아 사용하는 설계 방식 = 의존성 주입
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * 같은 이름의 회원은 존재하지 않는다 (이런 것들이 비즈니스 로직이 됨)
     */
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //Member 이름의 중복성 검사 함수
    private void validateDuplicateMember(Member member){
        /*
        //null이 반환될 수 있으므로 Optional로 감싸준 뒤 -> ifPresent(어떠한 값이 존재한다면) throw 실행
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        //위의 내용을 아래처럼 정리할 수 있음

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

