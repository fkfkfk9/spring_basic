package yoo.springlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /*
        만약 /로 매핑을 하지 않는다면 static에 index.html을 호출하지만
        /로 매핑을 하면 루트경로를 설정하였으므로 설정한 경로를 호출한다.
    */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
