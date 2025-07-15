package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

//인터페이스
//어떤 DB를 쓸지 아직 결정하지 못했음으로 저장소를 인터페이스의 형태로 구현했다

public interface MemberRepository {
    Member save(Member member); //저장소에 저장
    Optional<Member> findById(Long id); //id로 회원을 찾는 기능
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
