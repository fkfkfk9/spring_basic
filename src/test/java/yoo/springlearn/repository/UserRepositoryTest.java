package yoo.springlearn.repository;

import yoo.springlearn.domain.UserVO;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
    UserRepositoryImpl repository = new UserRepositoryImpl();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void insertUser() {
        UserVO vo = new UserVO();
       
        vo.setUserAge(33);
        vo.setUserId("tester");
        vo.setUserMail("abc@naver.com");
        vo.setUserName("YOO");

        repository.insertUser(vo);

        UserVO result = repository.findBySerialNo(vo.getSerialNo()).get();
        assertThat(vo).isEqualTo(result);
    }

    @Test
    public void findbyName() {
        UserVO vo = new UserVO();
       
        vo.setUserAge(33);
        vo.setUserId("tester");
        vo.setUserMail("abc@naver.com");
        vo.setUserName("YOO");

        repository.insertUser(vo);

        UserVO vo2 = repository.findByName("YOO").get();
        assertThat(vo2).isEqualTo(vo);
    }

    @Test
    public void findById() {
        UserVO vo = new UserVO();
       
        vo.setUserAge(33);
        vo.setUserId("tester");
        vo.setUserMail("abc@naver.com");
        vo.setUserName("YOO");

        repository.insertUser(vo);

        UserVO vo2 = repository.findById("tester").get();
        assertThat(vo2).isEqualTo(vo);
    }

    @Test
    public void findAll() {
        UserVO vo = new UserVO();
       
        vo.setUserAge(33);
        vo.setUserId("tester");
        vo.setUserMail("abc@naver.com");
        vo.setUserName("YOO");

        repository.insertUser(vo);

        UserVO vo2 = new UserVO();
       
        vo2.setUserAge(23);
        vo2.setUserId("tester2");
        vo2.setUserMail("abcdef@kakao.com");
        vo2.setUserName("Lee");

        repository.insertUser(vo2);

        List<UserVO> list = repository.findAll();
        assertThat(list.size()).isEqualTo(2);
    }    
}
