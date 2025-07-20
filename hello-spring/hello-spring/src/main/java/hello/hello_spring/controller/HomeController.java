package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// localhost:8080으로 요청이 오면 가장 먼저 컨트롤러를 뒤져 해당하는 매핑이 존재하는지 확인
// 존재한다면 해당 요청을 처리
// 존재하지 않는다면 static 파일의 index.html을 반환

@Controller
public class HomeController {
    @GetMapping("/")        //localhost8080에서 이쪽으로 연결
    public String home(){
        return "home";      //home.html을 리턴
    }
}
