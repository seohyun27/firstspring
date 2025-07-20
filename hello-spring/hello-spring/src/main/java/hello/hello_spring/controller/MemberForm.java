package hello.hello_spring.controller;

public class MemberForm {
    private String name; //createMemberForm.html의 "name" 변수와 일치되며 해당 값이 입력됨
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
