package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//컴포넌트 스캔과 자동 의존관계 설정
//@Controller, @Service, @Repository + @Autowired 처리

@Controller
public class MemberController {
    //스프링이 작동될 때 해당 컨트롤러를 생성한 후 가지고 있는다 -> 스프링 빈이 관리된다

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
