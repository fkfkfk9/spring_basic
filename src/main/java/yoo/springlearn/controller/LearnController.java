package yoo.springlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LearnController {

    @GetMapping("test")
    public String test(Model model) {
        model.addAttribute("data", "hello world");
        return "test";
    }

    @GetMapping(value="test-api")
    @ResponseBody
    public User TestApi(User user) {
        return user;
    }
    
    static class User{
        private String userId;
        private int age;
        private String name;

        public String getUserId() {
            return userId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        
        
    }
    
}
