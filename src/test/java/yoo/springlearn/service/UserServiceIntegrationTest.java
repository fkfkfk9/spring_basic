package yoo.springlearn.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import yoo.springlearn.domain.UserVO;
//import yoo.springlearn.repository.UserRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
//테스트에 트렌젝션을 넣으면 DB의 데이터가 자동으로 삭제된다.
@Transactional
public class UserServiceIntegrationTest {

    //@Autowired UserRepository repository;
    @Autowired UserService userService;

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
}
