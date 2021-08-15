package yoo.springlearn;

//import javax.persistence.EntityManager;
//import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

//import yoo.springlearn.repository.JdbcTemplateUserRepository;
//import yoo.springlearn.repository.JdbcUserRepository;
//import yoo.springlearn.repository.JpaUserRepository;
import yoo.springlearn.aop.TimeTraceAop;
import yoo.springlearn.repository.SpringDataJpaUserRepository;
import yoo.springlearn.repository.UserRepository;
//import yoo.springlearn.repository.UserRepositoryImpl;
import yoo.springlearn.service.UserService;

@Configuration
public class SpringConfig {

    // private DataSource dataSource;

    // @Autowired
    // public SpringConfig(DataSource dataSource){
    //     this.dataSource = dataSource;
    // }

//     private EntityManager em;
//
//     @Autowired
//     public SpringConfig(EntityManager em){
//         this.em = em;
//     }

    private final UserRepository userRepository;

    @Autowired
    public SpringConfig(SpringDataJpaUserRepository userRepository){
        this.userRepository = userRepository;
    }


    // 자바파일을 통해 직접 Bean객체를 등록하는것이 가능하다.
    // 컨트롤러는 bean으로 등록이 불가능하다.
    // 예전에는 XML로 등록했지만 최근에는 자바로 관리한다.
    @Bean
    public UserService userService(){
        //return new UserService(userRepository());
        return new UserService(userRepository);
    }

    //만들어준 AOP는 이렇게 직접 Bean으로 등록할 수 있다.
    /*@Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/

//    @Bean
//    public UserRepository userRepository(){
//        //return new UserRepositoryImpl();
//        //인터페이스를 만들어 상속을 통해 작업했기 때문에
//        //메모리 사용 방식에서 JDBC 사용 방식으로 손쉽게 변경할 수 있다.
//        //return new JdbcUserRepository(dataSource);
//        //return new JdbcTemplateUserRepository(dataSource);
//        return new JpaUserRepository(em);
//
//    }
}
