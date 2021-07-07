package yoo.springlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import yoo.springlearn.service.UserService;

@Controller
public class UserController {
    /*
        각각 Controller, Service, Repository 가 빈에 등록되는 이유는
        @Controller, @Service, @Repository 어노테이션을 넣어주기 때문인데
        해당 어노테이션에는 @component 어노테이션이 설정되어 빈으로 등록해준다.
        하지만 이런 어노테이션 등록은 Application 파일이 포함된 패키지 내부만 가능하고
        추가로 다른 패키지까지 빈으로 등록하려면 추가 설정이 필요하다.
        이런 빈들은 싱글톤 패턴으로 동록된다.
    */
     private UserService userService;

     @Autowired
     public UserController(UserService userService){
         this.userService = userService;
     }

     @GetMapping("/users/join")
     public String joinForm(){
         return "users/createUserForm";
     }
}
