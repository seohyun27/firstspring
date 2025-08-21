package hello.hello_spring.repository;

// JPA의 경우 쿼리문을 짤 필요조차 없음

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // EntityManager로 모든 것이 동작함
    // data-jpa 라이브러리를 받으면 스프링 부트가 자동으로 현재의 DB와 연동해 EntityManager를 생성
    // 주입 받아서 사용하기만 하면 된다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) { //생성자
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // JPA가 ID 셋부터 저장까지 보든 일을 다 해줌

        em.persist(member); // DB에 member를 영구저장
        return member; // 메소드의 리턴값에 맞춰주기 위해 멤버 객체를 리턴
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        // 해당 쿼리문에서 id에 null이 들어가면 자동으로 생성됨

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
