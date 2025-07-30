package hello.hello_spring.domain;

import jakarta.persistence.*;

// JPA = ORM(객체와 관계를 매핑)
// JPA 사용을 위해 @Entity, @Id, @GeneratedValue, @Column 등 필요

@Entity //JPA가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //직접 넣지 않고 DB가 알아서 생성
    private Long id; //데이터를 구분하기 위해 시스템이 저장하는 id. 사용자는 관여하지 않는다

    // @Column(name = "username") 
    // java의 변수명과 DB의 이름이 다를 때 사용
    // DB의 username과 매핑 가능
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
