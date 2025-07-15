package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//MVC : Model, View, Controller
//View는 오로지 화면에 관련된 일만
//비즈니스적인 처리는 Controller에서 처리
//두 개를 구분함으로써 코드의 유지보수성이 높아진다

//정적 컨텐츠, MVC, API는 각각 프론트와 소통하기 위한 방식
//세 가지의 방식이 반환하는 결과
//정적 컨텐츠 : 가공되지 않은 파일 (특별한 처리 없이 파일을 그대로 반환)
//MVC : 처리된 HTML
//API : 데이터

@Controller
public class HelloController {
    @GetMapping("hello") //hello로 접속하는 페이지는 이쪽에서 처리됨
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); //date 변수의 값으로 hello!!를 넘김
        return "hello"; //resources/templates 파일 아래의 hello를 찾아 리턴
    }

    @GetMapping("hello-mvc")
    //localhost:8080/hello-mvc?name=spring 이런 식으로 name의 파라미터를 넣어줘야 함
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template"; //name 변수의 값으로 html을 변환 후 웹 페이지 내에서 보여짐
    }

    //API 방식
    @GetMapping("hello-String")
    @ResponseBody //탭플릿 대신 응답 바디 부분에 직접 넣겠다
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
        //내가 입력한 name에 따라 "hello kim", "hello park" 등등이 그대로 리턴
        //html을 리턴하는 게 아니라 문자열을 내용으로서 리턴하게 됨
    }

    //API 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; 
        //위에서 만든 Hello 객체를 리턴
        //객체는 Json 방식(key,value)으로 바뀌어 리턴된다
        //필요하다면 'HTTP 메세지 컨버터'를 다른 걸로 선택함으로써 리턴 방식을 바꿀 수 있다
    }

    //클래스 생성
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
