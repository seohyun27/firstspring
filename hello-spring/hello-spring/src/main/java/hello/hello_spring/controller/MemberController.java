package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컴포넌트 스캔과 자동 의존관계 설정
// @Controller, @Service, @Repository + 각 클래스에 @Autowired 처리

// 스프링 빈 : 스프링이 대신 관리해주는 객체. 내가 직접 new를 할 필요 없이 스프링이 필요한 순간에 해당 객체를 찾아 넘겨준다
// 스프링 컨테이너 : 스프링 빈들을 담아두는 상자
//                 @Component, @Service, @Controller, @Repository 같은 어노테이션을 붙이면 스프링이 해당 클래스를 자동으로 빈으로 등록
//                  @Autowired를 통한 연결은 스프링 컨테이너에 올라가는 것들에서만 동작한다
// 싱글톤 : 스프링은 하나의 빈을 만들어 공유 -> 즉 컨트롤러, 서비스, 리포지토리를 하나만 만들어서 재사용


@Controller
public class MemberController {
    // 스프링이 작동될 때 해당 컨트롤러를 생성한 후 가지고 있는다 -> 스프링 빈이 관리된다

    private final MemberService memberService;

    @Autowired  //프로그램 시작 시
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){ //MemberForm 클래스의 setName을 통해 name 변수에 값이 들어가게 됨
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member); //해당 멤버를 가입

        return "redirect:/"; //회원가입이 끝났으니 홈 화면으로 복귀
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }



    /**
     * 의존 관계 설정법 3가지
     */

    /*
    // 1. 생성자 주입 -> 권장
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 2. 필드 주입
    // 중간에 교체할 수 있는 방법이 존재하지 않는다는 단점
    @Autowired private MemberService memberService;

    // 3. 세터 주입
    // 세터는 퍼블릭이므로 누구든지 접근할 수 있다는 단점
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    */
}
