package yoo.springlearn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import yoo.springlearn.repository.UserRepository;
import yoo.springlearn.repository.UserRepositoryImpl;
import yoo.springlearn.service.UserService;

@Configuration
public class SpringConfig {
    // 자바파일을 통해 직접 Bean객체를 등록하는것이 가능하다.
    // 컨트롤러는 bean으로 등록이 불가능하다.
    // 예전에는 XML로 등록했지만 최근에는 자바로 관리한다.
    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl();
    }
}
