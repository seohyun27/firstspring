package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//다중 상속 인터페이스

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JpaRepository가 기본적인 메소드를 모두 가지고 있다
    //구현체가 존재하지 않음
    //JpaRepository를 가지고 있으면 스프링 데이터 JPA가 알아서 구현체를 만들어서 등록해줌

    //이름으로 찾기, id로 찾기 같은 메소드는 공통 메소드로 존재할 수 없다
    //메소드의 이름, 파라미터, 반환 타입만 규칙에 맞춰 적으면 개발이 끝난다
    //단순한 작업 대부분은 여기서 끝나게 됨

    @Override
    Optional<Member> findByName(String name);
}
