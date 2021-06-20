package yoo.springlearn.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yoo.springlearn.domain.UserVO;
import yoo.springlearn.repository.UserRepository;
import yoo.springlearn.repository.UserRepositoryImpl;

import static org.assertj.core.api.Assertions.*;

public class UserServiceTest {

    UserRepositoryImpl repository;
    UserService userService;
    
    //@BeforeEach는 각각의 테스트가 실행되기전에 실행할 코드이고
    //@AfterEach는 테스트가 실행 된 후 실행되는 코드이다.

    @BeforeEach
    public void beforeEach(){
        repository = new UserRepositoryImpl();
        userService = new UserService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    //테스트 코드는 한글로 적어도 상관없다.
    //보통 테스트는 길어질 수 있으므로 주석으로 나눈다.
    //given은 주어진것 when : 이걸 실행했을대 then : 결과가 이것이 나와야 한다.

    @Test
    void 회원가입(){
        //given
        UserVO vo = new UserVO();
        vo.setUserName("YOO");
        vo.setUserAge(34);
        vo.setUserId("fkfkfk9");
        vo.setUserMail("fkfkfk9@naver.com");
        
        //when
        Long serialNo = userService.join(vo);

        //then
        UserVO dbvo = userService.findOne(serialNo).get();
        assertThat(vo.getUserName()).isEqualTo(dbvo.getUserName());
    }

    @Test
    void 중복_회원_예외(){
        //given
        UserVO vo = new UserVO();
        vo.setUserName("YOO");
        vo.setUserAge(34);
        vo.setUserId("fkfkfk9");
        vo.setUserMail("fkfkfk9@naver.com");
        
        UserVO vo2 = new UserVO();
        vo2.setUserName("YOO");
        vo2.setUserAge(34);
        vo2.setUserId("fkfkfk9");
        vo2.setUserMail("fkfkfk9@naver.com");
        //when
        userService.join(vo);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(vo2));
        
        //then
        assertThat(e.getMessage()).isEqualTo("이미 존제하는 회원입니다.");
    }

    @Test
    void findMembers(){
        
    }
    @Test
    void findOne(){
        
    }
}
