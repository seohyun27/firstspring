package hello.hello_spring.repository;

//MemoryMemberRepository를 테스트하고자 함

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hello.hello_spring.domain.Member;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test //save() 메소드를 실행해 봄
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //아이디는 여기서 자동으로 세팅됨

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result); //(기대하는 값, 실제 값)
    }
}
