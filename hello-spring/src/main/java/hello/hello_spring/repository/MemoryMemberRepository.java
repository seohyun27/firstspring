package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//MemberRepository의 구현
//가장 간단한 형태
//DB 대신 메모리에 저장 -> 자바를 내렸다가 다시 올리면 모든 데이터가 사라짐

// @Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버의 id를 시스템이 지정
        store.put(member.getId(), member); //이후 store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //찾으려는 id가 존재하지 않아 null이 반환될 수 있으므로 Optional로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //람다식으로 필터 처리
                .findAny(); //찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //value가 멤버임으로 멤버들을 반환하게 됨
    }

    public void clearStore(){
        store.clear();
    }

}
