package yoo.springlearn.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import yoo.springlearn.domain.UserVO;
import static org.assertj.core.api.Assertions.*;

public class UserServiceTest {

    UserService userService = new UserService();

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
        vo.setUserName("YOO");
        vo.setUserAge(34);
        vo.setUserId("fkfkfk9");
        vo.setUserMail("fkfkfk9@naver.com");
        //when
        Long serialNo = userService.join(vo);
        userService.join(vo2);
        // assertThrows(IllegalStateException.class, () -> userService.join(vo2));

        //then
    }

    @Test
    void findMembers(){
        
    }
    @Test
    void findOne(){
        
    }
}
