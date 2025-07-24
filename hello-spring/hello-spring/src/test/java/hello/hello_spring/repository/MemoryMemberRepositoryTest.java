package hello.hello_spring.repository;

//MemoryMemberRepository를 테스트하고자 함
//여러 명이 개발 or 만 라인 이상의 개발에서는 테스트가 필수적이다

//역으로 테스트 클래스를 먼저 작성한 이후에 이에 맞는 구현 클래스를 작성할 수도 있음
//테스트 주도 개발(TTD)

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hello.hello_spring.domain.Member;
import static org.assertj.core.api.Assertions.*; //assertThat() 메소드
import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //매 테스트마다 저장소를 지워줘야 원활한 테스트가 가능
    //-> 공용 데이터를 지워 테스트 간에 의존 관계가 없도록 설계
    @AfterEach //각 테스트 함수가 끝날 때마다 실행
    public void afterEach(){ //repository를 clear하는 메소드
        repository.clearStore();
    }

    @Test //save() 메소드를 실행해 봄
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //아이디는 여기서 자동으로 세팅됨

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result); //(기대하는 값, 실제 값)

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("String1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("String2");
        repository.save(member2);

        Member result = repository.findByName("String1").get();
        //Member result = repository.findByName("String2").get(); 로 작성할 경우 테스트 실패!!

        assertThat(result).isEqualTo(member1);
        //Assertions.assertEquals(member1, result);처럼 사용도 가능
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        //Assertions.assertEquals(2, result.size()); //사이즈가 2개인지만 간단하게 검증
        assertThat(result.size()).isEqualTo(2);
    }
}
